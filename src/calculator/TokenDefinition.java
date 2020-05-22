package calculator;

import java.util.regex.Pattern;

/**
 * This class is used to associate a regex to a token number
 */

public class TokenDefinition {

        public final Pattern regex;
        public final int token;

        public TokenDefinition(Pattern regex, int token) {
            this.regex = regex;
            this.token = token;
        }

}
