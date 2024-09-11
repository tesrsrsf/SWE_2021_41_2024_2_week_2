let rec fold3 func a lst1 lst2 lst3 = 
        match (lst1, lst2, lst3) with
        | ([], [], []) -> a
        | (bFirst::bRest, cFirst::cRest, dFirst::dRest) -> fold3 func (func a bFirst cFirst dFirst) bRest cRest dRest
        | _ -> -1       (* Invalid case *)

let () = 
        Format.printf "%d\n" (fold3 (fun a b c d -> a + b + c + d) 10 [33;67;12;33] [10;23;84;57] [11;55;23;58]);
        Format.printf "%d\n" (fold3 (fun a b c d -> (-a) + b + c + d) 4 [11;63;-45;22] [75;123;-44;1] [55;24;20;3]);
        Format.printf "%d\n" (fold3 (fun a b c d -> a * b * c * d) 55 [] [] []);
        Format.printf "%d\n" (fold3 (fun a b c d -> (a * b * c + d) mod 7) 33 [12;33] [10;7] [5;12]);
        Format.printf "%d\n" (fold3 (fun a b c d -> if b then a + c else a + d) 34 [true;false;false;true] [12;3;4;77] [11;23;6;100]);
        Format.printf "%d\n" (fold3 (fun a b c d -> if b then a else c + d) 55 [true;true;false;false;true] [111;63;88;123;98] [0;23;778;34;6])
