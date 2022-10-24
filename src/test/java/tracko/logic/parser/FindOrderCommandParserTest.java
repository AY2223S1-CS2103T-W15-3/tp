package tracko.logic.parser;

import static tracko.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tracko.logic.parser.CommandParserTestUtil.assertParseFailure;
import static tracko.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import tracko.logic.commands.order.FindOrderCommand;
import tracko.logic.parser.order.FindOrderCommandParser;
import tracko.model.order.OrderMatchesFlagsAndPrefixPredicate;

public class FindOrderCommandParserTest {
    // TODO: Update test cases according to new implementations

    private FindOrderCommandParser parser = new FindOrderCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindOrderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindOrderCommand expectedFindOrderCommand =
                new FindOrderCommand(
                        new OrderMatchesFlagsAndPrefixPredicate(Arrays.asList("Alice", "Bob"),
                                Collections.EMPTY_LIST, Collections.EMPTY_LIST,
                                false, false, false, false));
        assertParseSuccess(parser, " n/Alice Bob", expectedFindOrderCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/ \n Alice \n \t Bob  \t", expectedFindOrderCommand);
    }

}
