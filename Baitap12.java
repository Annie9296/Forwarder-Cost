import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;


abstract class ForwarderCost {
    String brokerName;
    String freightType;
    int containerId;
    int containerCount;
    int skuCount;
    boolean containerForty;
    boolean containerTwenty;
    double containerFortyUnloadingFee;
    double containerTwentyUnloadingFee;
    

    public ForwarderCost(
    String brokerName,
    String freightType,
    int containerId,
    int skuCount,
    int containerCount,
    boolean containerForty,
    boolean containerTwenty,
    double containerFortyUnloadingFee,
    double containerTwentyUnloadingFee
    ){
        this.brokerName = brokerName;
        this.freightType = freightType;
        this.containerId = containerId;
        this.skuCount = skuCount;
        this.containerCount = containerCount;
        this.containerForty = containerForty;
        this.containerTwenty = containerTwenty;
        this.containerFortyUnloadingFee = containerFortyUnloadingFee;
        this.containerTwentyUnloadingFee = containerTwentyUnloadingFee;
    }
    public double getContainerUnloadingCharge() {
        if(freightType.equals("Float") && containerForty){
            return this.containerCount * this.containerFortyUnloadingFee;
        } else {
            if(freightType.equals("Float") && containerTwenty){
                return this.containerCount * this.containerTwentyUnloadingFee;
            } return this.containerCount * ((this.containerFortyUnloadingFee + this.containerTwentyUnloadingFee)/2);
        }
    }
    public abstract double containerFortyCharge();
    public abstract  double containerTwentyCharge();
    
}
interface SortingFee {
    double countSortingCharge();
}
interface HandlingFee {
    double countHandlingCharge();
}
class BrokerCharge extends ForwarderCost{
    public BrokerCharge (
        String brokerName,
    String freightType,
    int containerId,
    int skuCount,
    int containerCount,
    boolean containerForty,
    boolean containerTwenty,
    double containerFortyUnloadingFee,
    double containerTwentyUnloadingFee){
        super(brokerName, freightType, containerId,skuCount,containerCount,containerForty,containerTwenty,containerFortyUnloadingFee,containerTwentyUnloadingFee);}
    @Override
public double containerFortyCharge() {
    return containerForty ? getContainerUnloadingCharge() : 0;
}

@Override
public double containerTwentyCharge() {
    return containerTwenty ? getContainerUnloadingCharge() : 0;
}}
    
class BrokerChargeOther extends ForwarderCost implements HandlingFee, SortingFee{

    public BrokerChargeOther(String brokerName,
    String freightType,
    int containerId,
    int skuCount,
    int containerCount,
    boolean containerForty,
    boolean containerTwenty,
    double containerFortyUnloadingFee,
    double containerTwentyUnloadingFee){
        super(brokerName,
    freightType,
    containerId,
    skuCount,
    containerCount,
    containerForty,
    containerTwenty,
    containerFortyUnloadingFee,
    containerTwentyUnloadingFee);
        
    }
    @Override
    public double countHandlingCharge(){
        if(this.containerForty){return ((this.containerCount-1) * 150);}
        if (this.containerTwenty){return ((this.containerCount-1)*70);}
    return 0.0;
    }
    @Override
    public double countSortingCharge(){
        if(skuCount >=1 && this.skuCount <= 5){
            return 5.0;
        } 
            if(skuCount >5){return (5.0 + (this.skuCount-5)*0.5);}
         return 0.0;}
    
    @Override
    public double containerFortyCharge(){
        return containerForty ? getContainerUnloadingCharge(): 0;
    }
    @Override
    public double containerTwentyCharge(){
        return containerTwenty ? getContainerUnloadingCharge(): 0;
    }
}


public class Baitap12 {
    public static void main(String[] args) {

        try {
    int containerCount = 0;

    double cost =
        350 / containerCount;

    System.out.println(cost);

} catch (ArithmeticException e) {

    System.out.println(
        "Container count cannot be 0"
    );
}
        ArrayList<ForwarderCost> shipmentDetail = new ArrayList<>();
        shipmentDetail.add(new BrokerCharge("FLEXPORT", "Float", 123, 1290, 10, true, false, 350, 0));
        shipmentDetail.add(new BrokerCharge("HELLMANN", "Float", 124, 10, 1, false, true, 0, 200));
        shipmentDetail.add(new BrokerCharge("OCL", "Float", 125, 20, 1, false, true, 0, 200));
        shipmentDetail.add(new BrokerCharge("TOBICA", "Float", 125, 5, 1, false, true, 0, 200));
        shipmentDetail.add(new BrokerCharge("FLEXPORT", "Float", 127, 12590, 25, true, false, 350, 0));
        shipmentDetail.add(new BrokerCharge("FLEXPORT", "Float", 128, 17889, 30, true, false, 350, 0));

        HashSet<Integer> containerId  = new HashSet<>();

        HashMap<String, Double> brokerReport = new HashMap<>();
   

        System.out.println("==== Shipment Forwarder Report =====");
        double totalBrokerCharge =0;

        for(ForwarderCost fc: shipmentDetail){
            double sortingFeeCost =0;
            double handlingFeeCost =0;

              if (!containerId.add(fc.containerId)) {
        System.out.printf(
            "Duplicate Container ID Found: %d \n",fc.containerId);
    }

            if( fc instanceof SortingFee){
                sortingFeeCost = ((SortingFee) fc).countSortingCharge();
            }
            if (fc instanceof HandlingFee){
                handlingFeeCost = ((HandlingFee) fc).countHandlingCharge();
            }
            double containerFortyChargeFinal = fc.containerFortyCharge();
            double containerTwentyChargeFinal = fc.containerTwentyCharge();
            double containerChargeFinal = fc.containerFortyCharge() + fc.containerTwentyCharge();
            double subtotalBrokerCharge = fc.containerFortyCharge() + fc.containerTwentyCharge() + sortingFeeCost + handlingFeeCost;
            totalBrokerCharge += subtotalBrokerCharge;


            double internalDebtBroker = brokerReport.getOrDefault(fc.brokerName, 0.0);
            brokerReport.put(fc.brokerName, internalDebtBroker + subtotalBrokerCharge);



            System.out.printf("Broker Name: %s | Shipment id: %d | Container 40: %b | Container 20: %b | Container Qty: %d | Container Charge: %.2f | Container Sorting Fee: %.2f | Container Handling Fee: %.2f | Subtotal: %.2f \n",
                fc.brokerName,
            fc.containerId,
                fc.containerForty,
                fc.containerTwenty,
                fc.containerCount,
                containerChargeFinal,
                sortingFeeCost,
                handlingFeeCost,
            subtotalBrokerCharge);

        }
        try{
            FileWriter fileWriter = new FileWriter("BrokerReport.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println("===========================");
            printWriter.println("BRPKER REPORT");
            printWriter.println("==========================");

            for (Map.Entry<String, Double> ledger : brokerReport.entrySet()) {


            
            printWriter.printf("Broker: %s  | Total charge by broker: %.2f USD \n",ledger.getKey(), ledger.getValue());
        }

        printWriter.println("-------------");
        printWriter.println("Export By : System automation");
        printWriter.close();

        System.out.println("Success exporting. Check your download folder");



        } catch(IOException e){
            System.out.println("Failed Exporting. " + e.getMessage());
        }

        try {
            FileReader fileReader= new FileReader("brokerConfig.txt");
            Scanner scanner = new Scanner(fileReader);
            System.out.printf("%-12s | %-12s | %-15s | %-15s \n","brokerName","freightType","ContainerFortyUnloadingFee","ContainerTwentyUnloadingFee");
            System.out.println("================================");


            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] dataParts = line.split("\\s+");

                 if(dataParts.length < 4){

        System.out.println(
            "Invalid Record: " + line
        );

        continue;}

                String brokerName = dataParts[0];
                String freightType = dataParts[1];
                double containerFortyUnloadingFee = Double.parseDouble(dataParts[2]);
                double containerTwentyUnloadingFee = Double.parseDouble(dataParts[3]);

                System.out.printf("%-12s | %-12s | %-15.2f | %-15.2f \n", brokerName, freightType, containerFortyUnloadingFee, containerTwentyUnloadingFee);
            }
            scanner.close();

        } catch (IOException e) {
            System.out.println("Not found file broker Config ");
        }
       

        System.out.println("Total Forwarder Charge " + totalBrokerCharge + " USD");

        System.out.println("Unique Containers: " + containerId.size());

    }
}
