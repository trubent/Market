import java.util.ArrayList;
import java.util.List;

interface QueueBehaviour {
    void enqueue(String person);
    String dequeue();
    void acceptOrder(String person);
    void releaseOrder(String person);
}

interface MarketBehaviour {
    void customerArrives(String person);
    void customerLeaves(String person);
    void updateMarket();
}

class Market implements QueueBehaviour, MarketBehaviour {
    private List<String> queue;

    public Market() {
        this.queue = new ArrayList<>();
    }

    @Override
    public void enqueue(String person) {
        queue.add(person);
        System.out.println(person + " добавлен в очередь.");
    }

    @Override
    public String dequeue() {
        if (!queue.isEmpty()) {
            String person = queue.remove(0);
            System.out.println(person + " убран из очереди.");
            return person;
        }
        return null;
    }

    @Override
    public void acceptOrder(String person) {
        System.out.println(person + " принял заказ.");
    }

    @Override
    public void releaseOrder(String person) {
        System.out.println(person + " отдал заказ.");
    }

    @Override
    public void customerArrives(String person) {
        enqueue(person);
        System.out.println(person + " пришел в магазин.");
    }

    @Override
    public void customerLeaves(String person) {
        System.out.println(person + " ушел из магазина.");
    }

    @Override
    public void updateMarket() {
        if (!queue.isEmpty()) {
            String person = queue.get(0);
            acceptOrder(person);
            releaseOrder(person);
            dequeue();
        } else {
            System.out.println("В очереди никого нет.");
        }
    }

    public void displayQueue() {
        System.out.println("Очередь: " + queue);
    }
}

public class Main {
    public static void main(String[] args) {
        Market market = new Market();
        market.customerArrives("Покупатель 1");
        market.customerArrives("Покупатель 2");
        market.updateMarket();
        market.customerArrives("Покупатель 3");
        market.updateMarket();
        market.updateMarket();
        market.displayQueue();
    }
}
