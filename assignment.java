

///////////////////////		McDonald's Systems /////////////////15L-4242


abstract class McDonalds{//abstract class
	
	int OrderID;
	String ProductName;
	int Price;
	String OrderStatus;
	
	
	
	public McDonalds(int OrderID,String ProductName,int Price, String OrderStatus){
		//non abstract methods
		this.OrderID=OrderID;
		this.ProductName=ProductName;
		this.Price=Price;
		this.OrderStatus=OrderStatus;
	}
	
	public void Preparing(){
		OrderStatus="Preparing your Order !!";
	}
	
	public void Waiting(){
		OrderStatus="Order is in Queue...";
	}
	
	
	
	public abstract void  DeliveredOrder();//abstract method
	
}

interface KitchenI {
	
	public abstract void cooking();
	public abstract void frying();
	public abstract void coldserve();
	
}

interface CustomerI {
	
	public abstract void burger();
	public abstract void fries();
	public abstract void icecream();
	public abstract void shakes();
	
}


class Food extends McDonalds implements KitchenI,CustomerI {
    
    
    class KitchenWork{ //non static nested class
       
        int burgersOrder;
        int friesOrder;
        int shakesOrder;
        int icecreamOrder;

        int burgersReady;
        int friesReady;
        int shakesReady;
        int icecreamReady;
        
        public void addcustomer(int burgers,int fries,int shakes, int icecream){
            
            burgersOrder=burgers;
            friesOrder=fries;
            shakesOrder=shakes;
            icecreamOrder=icecream;
        }
        
        public void readyitems(int burgers,int fries,int shakes, int icecream){
            
            burgersReady=burgers;
        	friesReady=fries;
       		shakesReady=shakes;
       		icecreamReady=icecream;
    }
    
    
    Food(int OrderID,String ProductName,int Price, String OrderStatus){
        
        super(OrderID,ProductName,Price,OrderStatus);
    }
    
    
    
    static class Preparation{//static nested class
        
        String[] items;
        
        Preparation(){
            
            items=new String[10];
            
            
        }
        
        public void printitems(){
            
            System.out.println("All ITEMS\n");
            for (int i=0;i<9;i++){
                int j=i+1;
                System.out.println(j+". "+items[i]+"\n");
            }
        }
        
        public void additem(int num,String ProductName){
            items[num-1]=ProductName;
        }
    }
    
    
    
    
	
	public void cooking(){
		System.out.println("Burgers are being cooked in kitchen\n");
	}
	
	public void frying(){
		System.out.println("Fries are in process\n");
	}
	
	public void coldserve(){
		System.out.println("Your icecream/shake will be served soon\n");
	}
	
	public void burger(){
		System.out.println("Burger(s) added to menu !\n");
	}
	
	public void fries(){	
		System.out.println("Fries has been added to menu !\n");
	}
	public void icecream(){	
		System.out.println("Icecream(s) added to menu !\n");
	}
	public void shakes(){	
		System.out.println("shakes(s) added to menu !\n");
	}
	public void DeliveredOrder(){	
		System.out.println("Order has been Delevered, Come again !!\n");
	}
	
}

public class mainClass{
    
    public static void main(String args[]){
	
	    McDonalds Customer1 = new Food(001, "McBig", 350, "Preparing");
	    
	    //interface class functions
	    Customer1.burger();
	    Customer1.fies();
	    Customer1.shakes();
	    Customer1.cooking();
	    Customer1.frying();
	    Customer1.coldserve();

	    
	    Customer1.DeliveredOrder();
	    
	    
	    //static nested class and it's fuctions
	    Food.Preparation items = new Food.Preparation();
	    items.additem(1, "McFlurry Icecream");
	    items.additem(2,"McRoyale Burger");
	    items.additem(3,"Cappucuino");
	    items.printitems();
	    
	    
    }    
    
}
