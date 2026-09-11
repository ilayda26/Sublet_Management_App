import java.util.LinkedHashMap;
import java.util.Map;

public class BerlinLocations {

    // Berlin boroughs and their neighbourhoods
    private static final Map<String, String[]> locations = new LinkedHashMap<>();

    static {
        locations.put("Mitte", new String[]{
                "Mitte",
                "Moabit",
                "Hansaviertel",
                "Tiergarten",
                "Wedding",
                "Gesundbrunnen"
        });

        locations.put("Friedrichshain-Kreuzberg", new String[]{
                "Friedrichshain",
                "Kreuzberg"
        });

        locations.put("Pankow", new String[]{
                "Prenzlauer Berg",
                "Weißensee",
                "Blankenburg",
                "Heinersdorf",
                "Karow",
                "Stadtrandsiedlung Malchow",
                "Pankow",
                "Blankenfelde",
                "Buch",
                "Französisch Buchholz",
                "Niederschönhausen",
                "Rosenthal",
                "Wilhelmsruh"
        });

        locations.put("Charlottenburg-Wilmersdorf", new String[]{
                "Charlottenburg",
                "Westend",
                "Charlottenburg-Nord",
                "Wilmersdorf",
                "Schmargendorf",
                "Grunewald",
                "Halensee"
        });

        locations.put("Spandau", new String[]{
                "Spandau",
                "Haselhorst",
                "Siemensstadt",
                "Staaken",
                "Gatow",
                "Kladow",
                "Hakenfelde",
                "Falkenhagener Feld",
                "Wilhelmstadt"
        });

        locations.put("Steglitz-Zehlendorf", new String[]{
                "Steglitz",
                "Lichterfelde",
                "Lankwitz",
                "Zehlendorf",
                "Dahlem",
                "Nikolassee",
                "Wannsee",
                "Schlachtensee"
        });

        locations.put("Tempelhof-Schöneberg", new String[]{
                "Schöneberg",
                "Friedenau",
                "Tempelhof",
                "Mariendorf",
                "Marienfelde",
                "Lichtenrade"
        });

        locations.put("Neukölln", new String[]{
                "Neukölln",
                "Britz",
                "Buckow",
                "Rudow",
                "Gropiusstadt"
        });

        locations.put("Treptow-Köpenick", new String[]{
                "Alt-Treptow",
                "Plänterwald",
                "Baumschulenweg",
                "Johannisthal",
                "Niederschöneweide",
                "Altglienicke",
                "Adlershof",
                "Bohnsdorf",
                "Oberschöneweide",
                "Köpenick",
                "Friedrichshagen",
                "Rahnsdorf",
                "Grünau",
                "Müggelheim",
                "Schmöckwitz"
        });

        locations.put("Marzahn-Hellersdorf", new String[]{
                "Marzahn",
                "Biesdorf",
                "Kaulsdorf",
                "Mahlsdorf",
                "Hellersdorf"
        });

        locations.put("Lichtenberg", new String[]{
                "Friedrichsfelde",
                "Karlshorst",
                "Lichtenberg",
                "Fennpfuhl",
                "Rummelsburg",
                "Falkenberg",
                "Malchow",
                "Wartenberg",
                "Neu-Hohenschönhausen",
                "Alt-Hohenschönhausen"
        });

        locations.put("Reinickendorf", new String[]{
                "Reinickendorf",
                "Tegel",
                "Konradshöhe",
                "Heiligensee",
                "Frohnau",
                "Hermsdorf",
                "Waidmannslust",
                "Lübars",
                "Wittenau",
                "Märkisches Viertel",
                "Borsigwalde"
        });
    }

    public static String[] getBoroughs() {
        return locations.keySet().toArray(new String[0]);
    }

    public static String[] getNeighbourhoods(String borough) {
        return locations.getOrDefault(borough, new String[0]);
    }

    // Checks that a neighbourhood belongs to the selected borough
    public static boolean validLocation(String borough, String neighbourhood) {

        String[] neighbourhoods = getNeighbourhoods(borough);

        for (String area : neighbourhoods) {
            if (area.equals(neighbourhood)) {
                return true;
            }
        }

        return false;
    }
}