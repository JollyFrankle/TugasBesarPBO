package exception;

import connection.DbConnection;

public class TanggalAmbilInvalidException extends Exception{
    private String tglSelesai;
    private String tglAmbil;

    public TanggalAmbilInvalidException(String tglSelesai, String tglAmbil) {
        this.tglSelesai = tglSelesai;
        this.tglAmbil = tglAmbil;
    }
    
    @Override
    public String toString(){
        return "Tanggal ambil yang diisi (" + this.tglAmbil + ")\r\ntidak boleh sebelum tanggal selesai (" + this.tglSelesai + ")!";
    }
}
