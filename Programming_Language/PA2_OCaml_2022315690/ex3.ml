let rec findUsableFactor num index =    (* This function finds the first divisible number of num starts from index *)
        if num mod index = 0 then index
        else let nextIndex = index + 1 in findUsableFactor num nextIndex

let rec calcN num factor =    (* Calculate the exponential part of a number and the num left *)
        if num mod factor = 0 then
                let (remainder, n) = calcN (num/factor) factor in (remainder, n + 1)
        else
                (num, 0)

let rec fibo n factor result num = 
        if n < factor then result
        else
                if n mod factor = 0 then
                        let (remain, pow) = calcN n factor in
                        fibo remain (factor + 1) ((factor, pow) :: result) num
                else
                        fibo n (factor + 1) result num
let reverseList data = 
        List.rev data

let rec isPrime num index =     (* Judge if a number is prime or not, if yes, then true, and vise versa*)
        if index >= num then true
        else if num mod index = 0 then false
        else (isPrime num (index + 1))


let printResult num = 
        let result = reverseList (fibo num 2 [] num) in
        let rec printTuple tup = 
                match tup with
                | [] -> ()
                | [last] -> Format.printf "(%d, %d)" (fst last) (snd last)
                | (base, pow) :: tail -> 
                                Format.printf "(%d, %d); " base pow;
                                printTuple tail
        in
        Format.printf "[";
        printTuple result;
        Format.printf "]\n"


let () = 
        printResult 10;
        printResult 17;
        printResult 27;
        printResult 315;
        printResult 777;
        printResult 1024
