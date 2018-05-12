import java.util.ListResourceBundle;

public class InfoBundle_ca extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
            { "Author", "Sergi Mascaró" },
            { "Nationality", "Barcelona, Catalunya" },
            { "Studies", "Enginyeria Informàtica i Tecnologies de la Informació" },
            { "HomeUni", "Universitat Politècnica de Catalunya"},
            { "Birth", "27/12/1995"},
            { "Course", "Component Programming"},
            { "GuestUni", "Politechnika Lodzka"}
        };

}
