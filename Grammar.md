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

## Grammar :

instruction -> expression
                | Epsilon

expression -> expression PLUS expression
                | expression MINUS expression
                | expression MULT expression
                | expression DIV expression
                | OP_BRACKET expression CL_BRACKET
                | NUMBER
                
complicated to code let's try another one

## Second Grammar

instruction -> expression
                | Epsilon
                
expression -> term sum

sum -> PLUS term sum | MINUS term sum | Epsilon

term -> NUMBER





