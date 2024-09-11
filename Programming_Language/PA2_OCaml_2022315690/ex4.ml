let rec checkCoprime x y index =        (* Check if two numbers are coprime, return true if they coprime and vise versa *)
        if x >= y then
                if y >= index then
                        if x mod index = 0 then
                                if y mod index = 0 then
                                        false
                                else
                                        checkCoprime x y (index + 1)
                        else
                                checkCoprime x y (index + 1)
                else
                        true
        else
                checkCoprime y x index


let rec phiCalc m index count =
        if index = m then
                count
        else
                if (checkCoprime m index 2) = true then
                         phiCalc m (index + 1) (count + 1)
                else
                         phiCalc m (index + 1) count

let phi m = 
        phiCalc m 1 0


let () =
        Format.printf "%d\n" (phi 4);
        Format.printf "%d\n" (phi 9);
        Format.printf "%d\n" (phi 10);
        Format.printf "%d\n" (phi 17);
        Format.printf "%d\n" (phi 30)
