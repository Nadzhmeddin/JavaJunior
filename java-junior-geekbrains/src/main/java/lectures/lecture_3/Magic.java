package lectures.lecture_3;


import jakarta.persistence.*;

@Entity
@Table(name ="magic")
public class Magic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="название")
    private String name;
    @Column(name="повреждение")
    private int damage;
    @Column(name ="атака")
    private int attBonus;
    @Column(name = "броня")
    private int def;

    public Magic(String name, int damage, int attBonus, int def) {
        this.name = name;
        this.damage = damage;
        this.attBonus = attBonus;
        this.def = def;
    }

    public Magic() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setAttBonus(int attBonus) {
        this.attBonus = attBonus;
    }

    public void setDef(int def) {
        this.def = def;
    }

    @Override
    public String toString() {
        return "Magic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", damage=" + damage +
                ", attBonus=" + attBonus +
                ", def=" + def +
                '}';
    }
}
