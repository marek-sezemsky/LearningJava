package org.example.game.stellaris;

import java.util.List;

public class GameState {
    public GameStateHeader header;
    public List<Player> players;
    public List<Species> species;
    public List<Nebulas> nebula;
    public List<Pop> pops;
    public Long lastCreatedPop;
    public List<GalacticObject> galacticObjects;
    public StarbaseManager starbaseMgr;
    public List<Planet> planets;
    public List<Country> countries;
    public Federation federation;
    public Truce truce;
    public TradeDeal tradeDeal;
    public Long lastCreatedCountry;
    public Long lastRefugeeCountry;
    public Long lastCreatedSystem;
    public List<Leader> leaders;
    public List<Ship> ships;
    public List<Fleet> fleets;
    public List<FleetTemplate> fleetTemplate;
    /*
        last_created_fleet=144
        last_created_ship=825
        last_created_leader=156
        last_created_army=15
        last_created_design=702
     */
    public List<Army> armies;
    public List<Deposit> deposits;
    /*
        ground_combat={
        war={
        debris={
        missile={
        strike_craft={
    */
    public List<AmbientObject> ambientObjects;
    /*
        last_created_ambient_object=27
        camera_focus=7
        random_name_database={
        name_list{
     */
     // TODO Galaxy (+cont...)
    
    
}
