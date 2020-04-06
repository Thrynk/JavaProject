##Grammar :

expression -> signed_term sum <br>
sum -> PLUS term sum | MINUS term sum | Epsilon

signed_term -> PLUS term | MINUS term | term

term -> factor term_op <br>
term_op -> MULT signed_factor term_op | DIV signed_factor term_op | Epsilon

signed_factor -> PLUS factor | MINUS factor | factor

factor -> argument factor_op <br>
factor_op -> RAISED signed_factor | Epsilon

argument -> value | FUNCTION argument | OP_BRACKET expression CL_BRACKET

value -> NUMBER