package cn.ksmcbrigade.iis.mixin;

import cn.ksmcbrigade.iis.InfinityItems;
import net.minecraft.entity.Entity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public class ItemInfinityMixin {
    @Inject(method = "inventoryTick",at = @At("TAIL"))
    public void inventoryTick(ItemStack p_41404_, World p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_, CallbackInfo ci) {
        if(!p_41404_.isEmpty() && !(p_41404_.getItem() instanceof BlockItem) && InfinityItems.HAS(p_41404_.getEnchantmentTags())){
            p_41404_.setDamageValue(0);
            if(p_41404_.getCount()==1 && p_41404_.getMaxStackSize()!=1){
                p_41404_.setCount(2);
            }
        }
    }
}
