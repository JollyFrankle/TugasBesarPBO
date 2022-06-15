package exception;

public class BelumAmbilException extends Exception{
    @Override
    public String toString(){
        return "Pesanan ini belum diambil, transaksi tidak dapat dihapus!\r\nSet tanggal pengambilan dulu sebelum transaksi ini dapat dihapus.";
    }
}
