let rec len inputList = 
        match inputList with
        | [] -> 0
        | _ :: t -> 1 + (len t)

let rec reverse inputList = 
        match inputList with
        | [] -> []
        | first :: rest -> reverse rest @ [first]

let palindrome inputList = 
        let reversedList = reverse inputList in
        if reversedList = inputList then true
        else false

let () = 
        Format.printf "%b\n" (palindrome ["1"; "2"; "3"; "4"]);
        Format.printf "%b\n" (palindrome ["x"; "m"; "a"; "s"]);
        Format.printf "%b\n" (palindrome ["a"; "m"; "o"; "r"; "e"; "r"; "o"; "m"; "a"]);
        Format.printf "%b\n" (palindrome ["1"; "2"; "3"; "2"; "1"]);
        Format.printf "%b\n" (palindrome ["b"; "o"; "r"; "r"; "o"; "w"; "o"; "r"; "r"; "o"; "b"])

