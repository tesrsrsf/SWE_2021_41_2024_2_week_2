let rec isPrime num index = 
        if index >= num then true
        else if num mod index = 0 then false
        else (isPrime num (index + 1))


let isEven num = 
        if num mod 2 = 0 then true
        else false


let rec getNextPrime num = 
        if (isPrime (num + 1) 2) = true then (num + 1)
        else (getNextPrime (num + 1))


let rec findPair num n a = 
        if a >= num then
                (-1, (-1, -1))
        else
                if (isPrime a 2) = true then 
                        let b = (num - a) in 
                        if (isPrime b 2) then 
                                (num, (a, b))
                        else
                                findPair num n (getNextPrime a)
                else
                        findPair num n (getNextPrime a)


let rec goldbachCalc a b n result index = 
        if (isEven index) = true then
                if index > b then
                        result
                else
                        let pair = (findPair index n 2) in 
                        if pair != (-1, (-1, -1)) then
                                goldbachCalc a b n (pair :: result) (index + 1)
                        else
                                goldbachCalc a b n result (index + 1)
        else
                goldbachCalc a b n result (index + 1)


let rec findMinA acc_min lst =
        match lst with
        | [] -> acc_min
        | (_, (a, _)) :: tail when a < acc_min -> (findMinA acc_min tail)
        | _ :: tail -> (findMinA acc_min tail)


let findMinACaller lst n = 
        let min_a = n in
        List.filter (fun (_, (a, _)) -> a = min_a) lst


let rec listFilter min result = 
        match result with
        | [] -> []
        | (n, (a, b)) :: tail -> 
                        if a >= min then
                                (n, (a, b)) :: (listFilter min tail)
                        else
                                listFilter min tail


let reverseList data = 
        List.rev data


let goldbach_list_limit a b n = 
        let result = (goldbachCalc a b n [] a) in
        let tempTup = listFilter n result in
        let validTup = reverseList tempTup in
        let rec printTuple tup = 
                match tup with
                | [] -> ()
                | [n, (a, b)] -> 
                                if (n, (a, b)) <> (-1, (-1, -1)) then
                                        if a <> b then
                                                Format.printf "(%d, (%d, %d))" n a b
                | (n, (a, b)) :: tail ->
                                if (n, (a, b)) <> (-1, (-1, -1)) then 
                                        if a <> b then (
                                                Format.printf "(%d, (%d, %d)); " n a b;
                                                printTuple tail
                                        )
                                        else
                                                printTuple tail
                                
                                else
                                        printTuple tail
        in
        Format.printf "[";
        printTuple validTup;
        Format.printf "]\n"

let () = 
        goldbach_list_limit 9 20 5;
        goldbach_list_limit 25 70 10;
        goldbach_list_limit 100 100 100;
        goldbach_list_limit 100 200 19;
        goldbach_list_limit 50 500 20;
        goldbach_list_limit 1 2000 50









