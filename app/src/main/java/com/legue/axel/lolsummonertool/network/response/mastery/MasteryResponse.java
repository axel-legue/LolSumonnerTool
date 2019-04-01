package com.legue.axel.lolsummonertool.network.response.mastery;

import java.util.List;

public class MasteryResponse {
    public int id;
    public String name;
    public String majorChangePatchVersion ;
    public String toolTip ;
    public String shortDesc ;
    public String longDesc ;
    // Exemple translation :
    // https://raw.communitydragon.org/9.6/game/assets/perks/styles/resolve/demolish/demolish.png
    public String iconPath ;
    public List<String> endOfGameStatDescs;





    public MasteryResponse() {
    }


}
