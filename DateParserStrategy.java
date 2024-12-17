import java.text.ParseException;
import java.util.Date;

interface DateParserStrategy {
    Date parse(String date) throws ParseException;
}
