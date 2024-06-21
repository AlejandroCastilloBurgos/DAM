package com.example.todosejercicios.ut07.dataPokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.todosejercicios.R;

import java.util.List;

public class Pokemon {
    private List<PokemonAbility> abilities;
    private List<PokemonStat> stats;
    public int id;
    public String name;
    public Sprites sprites;
    //getters y setters de la lista PokemonStats
    public List<PokemonStat> getStats() {
        return stats;
    }

    // Setters
    public void setStats(List<PokemonStat> stats) {
        this.stats = stats;
    }
    //clase stats encapsulada
    public class Stat{
        private String name;
        private String url;

        //getters
        public String getName() {
            return name;
        }
        public String getUrl() {
            return url;
        }

        //setters
        public void setName(String name) {
            this.name = name;
        }
        public void setUrl(String url) {
            this.url = url;
        }


    }
    //fin clase stats
    //clase stats
    public class PokemonStat{
        private String base_stat;
        private String effort;

        private Stat stat;

        //getters
        public String getBase_stat(){
            return base_stat;
        }
        public String getEffort(){
            return effort;
        }
        public Stat getStat(){
            return stat;
        }
        //setters
        public void setBase_stat(String base_stat) {
            this.base_stat = base_stat;
        }

        public void setEffort(String effort){
            this.effort = effort;
        }

        public void setStat(Stat stat){
            this.stat = stat;
        }

    }
    //fin clase stats

    //clase habilidad como esta encapsulada en el json hay que sacarla
    public class Ability {
        private String name;
        private String url;

        // Getters
        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }

        // Setters
        public void setName(String name) {
            this.name = name;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
    //fin de la clase habilidad
    //clase PokemonAbility (cuyo trabajo es la lista)
    public class PokemonAbility {
        private Ability ability;
        private boolean is_hidden;
        private int slot;

        // Getters
        public Ability getAbility() {
            return ability;
        }

        public boolean isHidden() {
            return is_hidden;
        }

        public int getSlot() {
            return slot;
        }

        // Setters
        public void setAbility(Ability ability) {
            this.ability = ability;
        }

        public void setHidden(boolean is_hidden) {
            this.is_hidden = is_hidden;
        }

        public void setSlot(int slot) {
            this.slot = slot;
        }
    }
    //fin clase PokemonAbility
    //getters y setters de la lista

    // Getters
    public List<PokemonAbility> getAbilities() {
        return abilities;
    }

    // Setters
    public void setAbilities(List<PokemonAbility> abilities) {
        this.abilities = abilities;
    }
    //fin getters y setters de la lista


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public class Sprites {
        private String front_default;
        private String back_default;
        private String front_shiny;
        private String back_shiny;

        public String getFrontDefault() {
            return front_default;
        }
        public String getBack_default(){
            return back_default;
        }

        public String getFront_shiny(){
            return front_shiny;
        }
        public String getBack_shiny(){
            return back_shiny;
        }
    }

}

