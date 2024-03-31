package cn.ksmcbrigade.iis.mixin;

import cn.ksmcbrigade.iis.InfinityItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public class BlockItemInfinityMixin {
    @Inject(method = "useOn",at = @At("RETURN"))
    public void useOn(ItemUseContext p_195939_1_, CallbackInfoReturnable<ActionResultType> cir) {
        ItemStack ITEM = p_195939_1_.getItemInHand();
        PlayerEntity player = p_195939_1_.getPlayer();
        if(cir.getReturnValue().consumesAction() && player!=null && !player.isCreative() && InfinityItems.HAS(ITEM.getEnchantmentTags()) && ITEM.getCount()<=1){
            ITEM.setCount(ITEM.getCount()+1);
        }
    }
}
