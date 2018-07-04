public class InfoPrinter {
    private static  Boolean isPrintingEnabled = true;

    public static void Print(String message)
    {
       System.out.println("Wykolali: \n Piotr Rostkowski,\n Marcin Migryt,\n Piotr Kozłowski,\n WSPOL,\n grupa I\n ");
        System.out.println("Program do zarządzania inwentarzem\n");

	   if(isPrintingEnabled){
            System.out.println(message);
        }
    }

    public static void Print(String format, String... args)
    {
        Print(String.format(format, args));
    }
}
