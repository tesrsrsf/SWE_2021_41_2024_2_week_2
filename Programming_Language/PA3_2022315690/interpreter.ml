module F = Format


let rec interp_expr (e: Ast.expr) (g: FStore.t) (s: Store.t) : Value.t = 
 (* Implement this function *)
        (* e is expr, g is the storage of functions, s is the storage of variable *)
        match e with
        |Num n -> Value.NumV n
        |Add (e1,e2) ->
                        let left = interp_expr e1 g s in
                        let right = interp_expr e2 g s in
                        (match (left, right) with
                        | (Value.NumV num_left, Value.NumV num_right) -> Value.NumV (num_left + num_right))
                        
        |Sub (e1,e2) -> 
                        let left = interp_expr e1 g s in
                        let right = interp_expr e2 g s in
                        (match (left, right) with
                        | (Value.NumV num_left, Value.NumV num_right) -> Value.NumV (num_left - num_right))
        |Id x -> (try Store.find x s with Not_found -> failwith ("Free identifier: " ^ x))
        |LetIn (x,e1,e2) -> 
                        (interp_expr e2 g (Store.add x (interp_expr e1 g s) s))
        |Call (f,args) -> 
                        let (parameter, content) = 
                                try FStore.find f g with Not_found -> failwith ("Undefined function: " ^ f) 
                        in
                        let required = (List.length parameter) in
                        let actual = (List.length args) in
                        if required <> actual then
                                failwith (Printf.sprintf "The number of arguments of %s mismatched: Required: %d, Actual: %d" f required actual)
                        else
                                let arg_val = List.map (fun arg -> interp_expr arg g s) args in
                                let temp_para = List.fold_left2 (fun sto parameter arg_val -> Store.add parameter arg_val sto) Store.empty parameter arg_val in 
                                interp_expr content g temp_para


let interp_fundef (d: Ast.fundef) (g: FStore.t) : FStore.t = 
 (* Implement this function *)
        match d with    (* The function will always operate this *)
        |Ast.FunDef (name, para, content) -> FStore.add name (para, content) g    (* store the function definition to g *)


let interp (p: Ast.prog) : Value.t = 
 (* Implement this function *)
        (* p is like "fundef list * expr (expr is the main expr)" *)
        match p with
        |Ast.Prog (fundefs, main_expr) -> 
                        let g = List.fold_left (fun fsto fundef -> interp_fundef fundef fsto) FStore.empty fundefs in 
                        interp_expr main_expr g Store.empty
        
