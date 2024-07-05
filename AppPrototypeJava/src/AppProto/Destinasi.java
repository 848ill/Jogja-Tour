package AppProto;

public class Destinasi {
    private String namaDestinasi;
    private String lokasi;
    private String imageUrl;
    private String deskripsi;
    private String waktuBuka;

    public Destinasi(String namaDestinasi, String lokasi, String imageUrl, String deskripsi, String waktuBuka) {
        this.namaDestinasi = namaDestinasi;
        this.lokasi = lokasi;
        this.imageUrl = imageUrl;
        this.deskripsi = deskripsi;
        this.waktuBuka = waktuBuka;
    }

    public String getNamaDestinasi() {
        return namaDestinasi;
    }
    public void setNamaDestinasi(String namaDestinasi) {
        this.namaDestinasi = namaDestinasi;
    }
    
    public String getLokasi() {
        return lokasi;
    }
    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDeskripsi() {
        return deskripsi;
    }
    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getWaktuBuka() {
        return waktuBuka;
    }
    public void setWaktuBuka(String waktuBuka) {
        this.waktuBuka = waktuBuka;
    }

}
