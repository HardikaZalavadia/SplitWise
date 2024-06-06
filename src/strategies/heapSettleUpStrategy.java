package strategies;

import dtos.Transaction;
import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class heapSettleUpStrategy implements SettleUpStrategy {
    @Override
    public List<Transaction> settleUpUser(Map<User, Integer> map) {

        PriorityQueue<Pair> receivers = new PriorityQueue<>();
        PriorityQueue<Pair> givers = new PriorityQueue<Pair>();

        List<Transaction> userTransaction = new ArrayList<>();

        for(User user : map.keySet()){
            Integer amount = map.get(user);

            if(amount<0){
                givers.add(new Pair(user, -1*amount));
            }
            else if(amount>0){
                receivers.add(new Pair(user, amount));
            }

        }
        while(receivers.size()>0){
            Pair receiver = receivers.poll();
            Pair giver = givers.poll();

            userTransaction.add(new Transaction(giver.user.getName(), receiver.user.getName(), giver.amount));

            if(giver.amount< receiver.amount){
                receivers.add(new Pair(receiver.user, receiver.amount- giver.amount));
            }


        }
        return userTransaction;

    }
    class Pair implements Comparable{
        private User user;
        private int amount;
        public Pair(User user, int amount){
            this.user = user;
            this.amount = amount;
        }
        public int compareTo(Object o){
            Pair other = (Pair) o;
            if(this.amount<= other.amount){
                return -1;
            }
            return 1;
        }
    }
}
