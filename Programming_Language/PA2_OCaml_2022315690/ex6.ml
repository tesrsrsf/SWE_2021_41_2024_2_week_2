let rec sigmaCalc a b f index result = 
        if index <= b then
                let newResult = result + (f index) in
                sigmaCalc a b f (index + 1) newResult
        else
                result



let sigma a b f = 
        sigmaCalc a b f a 0


let () = 
        Format.printf "%d\n" (sigma 10 10 (fun x -> x));
        Format.printf "%d\n" (sigma 11 10 (fun x -> x));
        Format.printf "%d\n" (sigma 10 5 (fun x -> x));
        Format.printf "%d\n" (sigma 1 10 (fun x -> if x mod 2 = 0 then 1 else 0));
        Format.printf "%d\n" (sigma 2 10 (fun x -> x + 10));
        Format.printf "%d\n" (sigma 0 100 (fun x -> 0));
        Format.printf "%d\n" (sigma 10 12 (fun x -> 2 * x))










