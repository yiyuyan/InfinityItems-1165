package cn.ksmcbrigade.iis.mixin;

import cn.ksmcbrigade.iis.InfinityItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BucketItem.class)
public class BucketMixin {

    @Inject(method = "getEmptySuccessItem", at = @At("RETURN"), cancellable = true)
    private static void getEmptySuccessItem(ItemStack p_40700_, PlayerEntity p_40701_, CallbackInfoReturnable<ItemStack> cir){
        if(InfinityItems.HAS(p_40700_.getEnchantmentTags())){
            cir.setReturnValue(p_40700_);
        }
    }

}
