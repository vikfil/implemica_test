import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
The class reads data from the defined file
and then on its base building the graph of cities
 */
public class DocumentParser {
    private Map<Integer, String> cityNameWithIndex;
    private Map<String, String> pairCityToSearch;
    private List<HashMap<Integer, Integer>>[] directEdges;
    private int cityAmount;
    private int skipTwoStep;

    public DocumentParser() {
        this.cityNameWithIndex = new HashMap<>();
        this.pairCityToSearch = new LinkedHashMap<>();
    }

    public void parseDocument(String address) {
        File file = new File(address);
        try(Scanner scanner = new Scanner(file)) {
            int flag = 0;
            int neighbors = 0;
            int cityIndex = 0;

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                    if (skipTwoStep == 2) {
                        cityAmount = Integer.parseInt(data);
                        initiateDirectEdges(cityAmount);
                    }

                    if (skipTwoStep > 2 && cityIndex >= flag && data.length() > 3 && neighbors == 0) {
                        cityIndex++;
                        cityNameWithIndex.put(cityIndex, data);
                        flag++;
                        continue;
                    }
                    if (skipTwoStep > 2 && cityIndex >= flag && data.length() == 1 && neighbors == 0) {
                       if (flag == cityAmount) {
                           flag++;
                       }
                        neighbors = Integer.parseInt(data);
                        continue;
                    }
                    if (data.split(" ").length > 1 && neighbors > 0) {
                        String[] arr = data.split(" ");
                        HashMap<Integer, Integer> map = new HashMap<>();
                        int destination =Integer.parseInt(arr[0]);
                        int cost = Integer.parseInt(arr[1]);
                        map.put(destination, cost);
                        directEdges[cityIndex].add(map);
                        neighbors--;
                        continue;
                    }
                    if (data.split(" ").length > 1 && neighbors == 0) {
                        String[] cities = data.split(" ");
                            pairCityToSearch.put(cities[0], cities[1]);
                    }
                    skipTwoStep++;
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, String> getCityNameWithIndex() {
        return cityNameWithIndex;
    }

    private void initiateDirectEdges(int size) {
        directEdges = new ArrayList[size + 1];
        for (int i = 0; i <= size; i++) {
            directEdges[i] = new ArrayList<>();
        }
    }

    public Map<String, String> getPairCityToSearch() {
        return pairCityToSearch;
    }

    public List<HashMap<Integer, Integer>>[] getDirectEdges() {
        return directEdges;
    }

    public int getCityAmount() {
        return cityAmount;
    }
}
