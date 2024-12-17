import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class SimpleDateParser implements DateParserStrategy {
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Override
    public Date parse(String date) throws ParseException {
        return formatter.parse(date);
    }
}
