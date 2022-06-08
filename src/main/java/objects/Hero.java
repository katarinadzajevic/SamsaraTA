package objects;

public class Hero {

    private String heroName;
    private String heroClass;
    private Integer heroLevel;
    private String username;
    private Long createdAt;

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    public Integer getHeroLevel() {
        return heroLevel;
    }

    public void setHeroLevel(Integer heroLevel) {
        this.heroLevel = heroLevel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }


}
