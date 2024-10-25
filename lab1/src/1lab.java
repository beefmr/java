import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
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

    // Дополнение иерархии классов новым типом кроссовок
    static class RunningSneakers extends Sneakers {
        public RunningSneakers(String name, float cost, Producer producer) {
            super(name, cost, "Running", producer);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Sneakers> sneakersList = new ArrayList<>();

        // Create sample sneakers
        sneakersList.add(new FootballSneakers("Nike Mercurial", 120.0f, new Sneakers.Producer("Nike", "USA")));
        sneakersList.add(new BasketballSneakers("Adidas Crazy Explosive", 150.0f, new Sneakers.Producer("Adidas", "Germany")));
        sneakersList.add(new FootballSneakers("Puma Future", 110.0f, new Sneakers.Producer("Puma", "Germany")));
        sneakersList.add(new BasketballSneakers("Under Armour Curry", 130.0f, new Sneakers.Producer("Under Armour", "USA")));
        sneakersList.add(new FootballSneakers("New Balance Furon", 100.0f, new Sneakers.Producer("New Balance", "USA")));
        sneakersList.add(new BasketballSneakers("Reebok Question", 140.0f, new Sneakers.Producer("Reebok", "USA")));
        sneakersList.add(new FootballSneakers("Asics DS Light", 160.0f, new Sneakers.Producer("Asics", "Japan")));
        sneakersList.add(new BasketballSneakers("Jordan 1", 180.0f, new Sneakers.Producer("Nike", "USA")));
        sneakersList.add(new FootballSneakers("Mizuno Morelia", 170.0f, new Sneakers.Producer("Mizuno", "Japan")));
        sneakersList.add(new BasketballSneakers("Converse Chuck Taylor", 75.0f, new Sneakers.Producer("Converse", "USA")));

        // Count producers
        Map<String, Integer> producerCount = new HashMap<>();
        Map<String, Float> totalCostPerProducer = new HashMap<>();
        Map<String, Integer> countPerProducer = new HashMap<>();
        Map<String, Float> totalCostPerType = new HashMap<>();
        Map<String, Integer> countPerType = new HashMap<>();

        for (Sneakers sneaker : sneakersList) {
            // Count producers
            String producerName = sneaker.producer.name;
            producerCount.put(producerName, producerCount.getOrDefault(producerName, 0) + 1);

            // Calculate total cost per producer
            totalCostPerProducer.put(producerName, totalCostPerProducer.getOrDefault(producerName, 0f) + sneaker.cost);
            countPerProducer.put(producerName, countPerProducer.getOrDefault(producerName, 0) + 1);

            // Calculate total cost per type
            totalCostPerType.put(sneaker.type, totalCostPerType.getOrDefault(sneaker.type, 0f) + sneaker.cost);
            countPerType.put(sneaker.type, countPerType.getOrDefault(sneaker.type, 0) + 1);
        }

        // Output results
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

        // Создание нового объекта RunningSneakers
        sneakersList.add(new RunningSneakers("Nike Air Zoom", 120.0f, new Sneakers.Producer("Nike", "USA")));

        // Обновление результатов после добавления нового объекта
        for (Sneakers sneaker : sneakersList) {
            // Обновление количества производителей
            String producerName = sneaker.producer.name;
            producerCount.put(producerName, producerCount.getOrDefault(producerName, 0) + 1);

            // Обновление общей стоимости по производителю
            totalCostPerProducer.put(producerName, totalCostPerProducer.getOrDefault(producerName, 0f) + sneaker.cost);
            countPerProducer.put(producerName, countPerProducer.getOrDefault(producerName, 0) + 1);

            // Обновление общей стоимости по типуФ
            totalCostPerType.put(sneaker.type, totalCostPerType.getOrDefault(sneaker.type, 0f) + sneaker.cost);
            countPerType.put(sneaker.type, countPerType.getOrDefault(sneaker.type, 0) + 1);
        }

        // Вывод обновленных результатов
        System.out.println("\nОбновленные результаты:");
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

        scanner.close();
    }
}