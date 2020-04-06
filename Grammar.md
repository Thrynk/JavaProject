# Basic Calculator grammar

##Tokens :

- PLUS : +

- MINUS : -

- MULT : *

- DIV : /

- POWER : ^

- FUNCTION : sin|cos|sqrt|exp

- OP_BRACKET : (

- CL_BRACKET : )

- NUMBER : numerical number

## Grammar

instruction -> expression
                | Epsilon
                
expression -> term sum

sum -> PLUS term sum | MINUS term sum | Epsilon

term -> NUMBER





