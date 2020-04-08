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

## Standard Grammar

instruction -> expression
                | Epsilon
                
expression -> term 
                | term PLUS term 
                | term MINUS term

term -> factor
        | factor MULT factor
        | factor DIV factor
            
factor -> NUMBER
            | OP_BRACKET expression CL_BRACKET

## LL Grammar transformation (suppression of left recursion)

use this technique : https://www.lewuathe.com/how-to-construct-grammar-of-arithmetic-operations.html

instruction -> expression
                | Epsilon
                
expression -> term sum

sum -> PLUS term sum 
        | MINUS term sum 
        | Epsilon

term -> factor factor_op

factor_op -> MULT factor factor_op 
                | DIV factor factor_op 
                | Epsilon
                
factor -> NUMBER
            | OP_BRACKET expression CL_BRACKET
                







