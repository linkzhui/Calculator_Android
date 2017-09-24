# Calculator_Android
Android Calculator with basic calculation function
A calculator can handle plus, minus, production, division, and clear operations for integers, with results rounded to the nearest
integer too.

Overflow handling: if the result is bigger than 9999999 or smaller than -9999999, show OVERFLOW! If any number of those ranges are typed in, show the same error message instantly as well.
If divided by zero,  show the error message ERROR!

Integer operations only
Always rounding to the closest integer; when ambiguity occurs, rounding away from 0; e.g.
2 / 5 = 0
8 / 9 = 1
-7 / 6 = -1
5 / 2 = 3
-5 / 2 = -3

It should ONLY use the last operand if multiple operands are entered consecutively
Example: “5++-1=” is equivalent to “5-1=”

Proper error handling and self correction.
“+-5” is equivalent to “negative 5”
0004 + 32 ++ 0101 - - 04= is equivalent to 4+32+101-4=
39 + 48 = - 10 + 5 - 98= is equivalent to 39+48-10+5-98=
Display should show 39, 48, 87, 10, 77,5, 82,98, -16
