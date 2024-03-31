package cn.ksmcbrigade.iis.mixin;

import cn.ksmcbrigade.iis.InfinityItems;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MilkBucketItem.class)
public class MilkBucketMixin {
    @Inject(method = "finishUsingItem",at = @At("RETURN"), cancellable = true)
    public void finishUsingItem(ItemStack p_42923_, World p_42924_, LivingEntity p_42925_, CallbackInfoReturnable<ItemStack> cir){
        if(InfinityItems.HAS(p_42923_.getEnchantmentTags())){
            ItemStack itemStack = Items.MILK_BUCKET.getDefaultInstance();
            itemStack.enchant(Enchantments.INFINITY_ARROWS,1);
            cir.setReturnValue(itemStack);
        }
    }
}
