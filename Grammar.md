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

- EQUAL : =

- VARIABLE = variable (only number for the moment)

## Standard Grammar

instruction -> expression
                | initialization
                | Epsilon
                
initialization -> INIT VARIABLE EQUAL NUMBER
                    | FUNCTION_EXPRESSION VARIABLE OP_BRACKET VARIABLE CL_BRACKET EQUAL expression
                
expression -> term_with_sign 
                | term_with_sign PLUS term 
                | term_with_sign MINUS term
                
term_with_sign -> PLUS term
                    | MINUS term
                    | term

term -> factor
        | factor MULT factor_with_sign
        | factor DIV factor_with_sign
        
factor_with_sign -> PLUS factor
                    | MINUS factor
                    | factor
            
factor -> value
            | FUNCTION factor
            | OP_BRACKET expression CL_BRACKET
            
value -> NUMBER
            | VARIABLE
            | FUNCTION_DEFINED

## LL Grammar transformation (suppression of left recursion)

use this technique : https://www.lewuathe.com/how-to-construct-grammar-of-arithmetic-operations.html

instruction -> expression
                | initialization
                | Epsilon
                
initialization -> INIT VARIABLE EQUAL NUMBER
                    | FUNCTION_EXPRESSION VARIABLE OP_BRACKET VARIABLE CL_BRACKET EQUAL expression
                
expression -> term_with_sign sum

sum -> PLUS term sum 
        | MINUS term sum 
        | Epsilon
        
term_with_sign -> PLUS term
                    | MINUS term
                    | term

term -> factor factor_op

factor_op -> MULT factor_with_sign factor_op 
                | DIV factor_with_sign factor_op 
                | Epsilon
                
factor_with_sign -> PLUS factor
                    | MINUS factor
                    | factor
                
factor -> value
            | FUNCTION factor
            | OP_BRACKET expression CL_BRACKET
            
value -> NUMBER
            | VARIABLE
            | FUNCTION_DEFINED
                







