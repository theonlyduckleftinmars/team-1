package model;

public class Player {
    private int id;
    private String codename;
    private int equipmentId;

    public Player(int id, String codename, int equipmentId) {
        this.id = id;
        this.codename = codename;
        this.equipmentId = equipmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeName() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", codename='" + codename + '\'' +
                ", equipmentId=" + equipmentId +
                '}';
    }
}
