
import java.util.ListResourceBundle;

public class InfoBundle_en extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
            { "Author", "Sergi Mascaró" },
            { "Nationality", "Barcelona, Catalonia" },
            { "Studies", "Computer science and Information Technology" },
            { "HomeUni", "Universitat Politècnica de Catalunya"},
            { "Birth", "27/12/1995"},
            { "Course", "Component Programming"},
            { "GuestUni", "Politechnika Lodzka"}
        };
}
