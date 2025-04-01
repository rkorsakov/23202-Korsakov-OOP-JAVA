//package factory.supplier;
//
//import factory.product.Product;
//import factory.storage.Storage;
//
//public class Supplier<T extends Product> {
//    private final Storage<T> storage;
//    private final Class<T> productClass;
//    private final int productionTime;
//    private boolean isRunning = true;
//
//    public void run() {
//        while (isRunning) {
//            T product;
//            try {
//                Thread.sleep(productionTime);
//                product = productClass.getDeclaredConstructor().newInstance();
//                storage.put(product);
//            } catch (Exception e) {
//                return;
//            }
//        }
//    }
//
//    public void stop() {
//        isRunning = false;
//    }
//}
