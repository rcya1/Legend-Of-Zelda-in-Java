package components.items.player;

import components.entity.Link;

//An item that exists in the player's inventory
public abstract class Item
{
    //Called when the player uses this item
    public abstract void action(Link link);
}
