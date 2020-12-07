package kz.iitu.pharm.drugrequest;

public class DrugRequest {
    private Long userId;
    private Drug drug;

    public DrugRequest() {
    }

    public DrugRequest(Long userId, Drug drug) {
        this.userId = userId;
        this.drug = drug;
    }

    public Long getUserId() {
        return userId;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    @Override
    public String toString() {
        return "DrugRequest{" +
                "userId=" + userId +
                ", drug=" + drug +
                '}';
    }
}
