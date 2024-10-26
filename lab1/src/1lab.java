import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class main {
    static class Sneakers {
        String name;
        float cost;
        String type;
        Producer producer;

        static class Producer {
            String name;
            String country;

            Producer(String name, String country) {
                this.name = name;
                this.country = country;
            }
        }

        public Sneakers(String name, float cost, String type, Producer producer) {
            this.name = name;
            this.cost = cost;
            this.type = type;
            this.producer = producer;
        }
    }

    static class FootballSneakers extends Sneakers {
        public FootballSneakers(String name, float cost, Producer producer) {
            super(name, cost, "Football", producer);
        }
    }

    static class BasketballSneakers extends Sneakers {
        public BasketballSneakers(String name, float cost, Producer producer) {
            super(name, cost, "Basketball", producer);
        }
    }

    static class RunningSneakers extends Sneakers {
        public RunningSneakers(String name, float cost, Producer producer) {
            super(name, cost, "Running", producer);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Создайте экземпляр Repository для управления кроссовками
        Repository<Sneakers> sneakersRepository = new Repository<>();

        // Создание образцов кроссовок и добавление их в репозиторий
        sneakersRepository.addItem(new FootballSneakers("Nike Mercurial", 120.0f, new Sneakers.Producer("Nike", "USA")));
        sneakersRepository.addItem(new BasketballSneakers("Adidas Crazy Explosive", 150.0f, new Sneakers.Producer("Adidas", "Germany")));
        sneakersRepository.addItem(new FootballSneakers("Puma Future", 110.0f, new Sneakers.Producer("Puma", "Germany")));
        sneakersRepository.addItem(new BasketballSneakers("Under Armour Curry", 130.0f, new Sneakers.Producer("Under Armour", "USA")));
        sneakersRepository.addItem(new FootballSneakers("New Balance Furon", 100.0f, new Sneakers.Producer("New Balance", "USA")));
        sneakersRepository.addItem(new BasketballSneakers("Reebok Question", 140.0f, new Sneakers.Producer("Reebok", "USA")));
        sneakersRepository.addItem(new FootballSneakers("Asics DS Light", 160.0f, new Sneakers.Producer("Asics", "Japan")));
        sneakersRepository.addItem(new BasketballSneakers("Jordan 1", 180.0f, new Sneakers.Producer("Nike", "USA")));
        sneakersRepository.addItem(new FootballSneakers("Mizuno Morelia", 170.0f, new Sneakers.Producer("Mizuno", "Japan")));
        sneakersRepository.addItem(new BasketballSneakers("Converse Chuck Taylor", 75.0f, new Sneakers.Producer("Converse", "USA")));

        // Получите список кроссовок из репозитория
        List<Sneakers> sneakersList = sneakersRepository.getItems();

        // Подсчет производителей и средней стоимости
        Map<String, Integer> producerCount = new HashMap<>();
        Map<String, Float> totalCostPerProducer = new HashMap<>();
        Map<String, Integer> countPerProducer = new HashMap<>();
        Map<String, Float> totalCostPerType = new HashMap<>();
        Map<String, Integer> countPerType = new HashMap<>();

        // Вызов метода для подсчета статистики
        calculateStatistics(sneakersList, producerCount, totalCostPerProducer, countPerProducer, totalCostPerType, countPerType);

        // Вывод результатов
        printResults(producerCount, totalCostPerProducer, countPerProducer, totalCostPerType, countPerType);

        // Создание нового объекта RunningSneakers
        RunningSneakers newRunningSneaker = new RunningSneakers("Nike Air Zoom", 120.0f, new Sneakers.Producer("Nike", "USA"));
        sneakersRepository.addItem(newRunningSneaker);

        // Обновление списка кроссовок
        sneakersList = sneakersRepository.getItems();
        // Обновление подсчетов
        producerCount.clear();
        totalCostPerProducer.clear();
        countPerProducer.clear();
        totalCostPerType.clear();
        countPerType.clear();

        // Вызов метода для подсчета статистики снова
        calculateStatistics(sneakersList, producerCount, totalCostPerProducer, countPerProducer, totalCostPerType, countPerType);

        // Вывод обновленных результатов
        System.out.println("\nОбновленные результаты:");
        printResults(producerCount, totalCostPerProducer, countPerProducer, totalCostPerType, countPerType);

        scanner.close();
    }

    // Метод для подсчета статистики
    private static void calculateStatistics(List<Sneakers> sneakersList,
                                            Map<String, Integer> producerCount,
                                            Map<String, Float> totalCostPerProducer,
                                            Map<String, Integer> countPerProducer,
                                            Map<String, Float> totalCostPerType,
                                            Map<String, Integer> countPerType) {
        for (Sneakers sneaker : sneakersList) {
            // Подсчет производителей
            String producerName = sneaker.producer.name;
            producerCount.put(producerName, producerCount.getOrDefault(producerName, 0) + 1);

            // Подсчет общей стоимости по производителю
            totalCostPerProducer.put(producerName, totalCostPerProducer.getOrDefault(producerName, 0f) + sneaker.cost);
            countPerProducer.put(producerName, countPerProducer.getOrDefault(producerName, 0) + 1);

            // Подсчет общей стоимости по типу
            totalCostPerType.put(sneaker.type, totalCostPerType.getOrDefault(sneaker.type, 0f) + sneaker.cost);
            countPerType.put(sneaker.type, countPerType.getOrDefault(sneaker.type, 0) + 1);
        }
    }

    // Метод для вывода результатов
    private static void printResults(Map<String, Integer> producerCount,
                                     Map<String, Float> totalCostPerProducer,
                                     Map<String, Integer> countPerProducer,
                                     Map<String, Float> totalCostPerType,
                                     Map<String, Integer> countPerType) {
        System.out.println("Количество производителей:");
        for (String producer : producerCount.keySet()) {
            System.out.println(producer + ": " + producerCount.get(producer));
        }

        System.out.println("\nСредняя стоимость обуви по каждому производителю:");
        for (String producer : totalCostPerProducer.keySet()) {
            float averageCost = totalCostPerProducer.get(producer) / countPerProducer.get(producer);
            System.out.println(producer + ": " + averageCost);
        }

        System.out.println("\nСредняя стоимость обуви по каждому типу:");
        for (String type : totalCostPerType.keySet()) {
            float averageCost = totalCostPerType.get(type) / countPerType.get(type);
            System.out.println(type + ": " + averageCost);
        }
    }
}