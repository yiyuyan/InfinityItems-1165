package cn.ksmcbrigade.iis;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.Commands;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Mod("iis")
@Mod.EventBusSubscriber
public class InfinityItems {

    public static int exp = 7;

    public InfinityItems() throws IOException {
        MinecraftForge.EVENT_BUS.register(this);
        File file = new File("config/iis-config.json");
        if(!file.exists()){
            JsonObject json = new JsonObject();
            json.addProperty("exp",exp);
            Files.write(file.toPath(),json.toString().getBytes());
        }
        exp = new JsonParser().parse(new String(Files.readAllBytes(file.toPath()))).getAsJsonObject().get("exp").getAsInt();
    }

    @SubscribeEvent
    public static void RegisterCommand(RegisterCommandsEvent event){
        event.getDispatcher().register(Commands.literal("inject").executes(context -> {
            PlayerEntity entity = (PlayerEntity)context.getSource().getEntity();
            if (entity != null) {
                if(!entity.getMainHandItem().isEmpty()){
                    if(entity.experienceLevel>=exp){
                        entity.getMainHandItem().enchant(Enchantments.INFINITY_ARROWS,1);
                        entity.giveExperienceLevels(-exp);
                    }
                    else{
                        entity.sendMessage(ITextComponent.nullToEmpty(I18n.get("commands.iis.cannot_xp").replace("{x}",String.valueOf(exp))),entity.getUUID());
                    }
                }
                else if(!entity.getOffhandItem().isEmpty()){
                    if(entity.experienceLevel>=exp){
                        entity.getOffhandItem().enchant(Enchantments.INFINITY_ARROWS,1);
                        entity.giveExperienceLevels(-exp);
                    }
                    else{
                        entity.sendMessage(ITextComponent.nullToEmpty(I18n.get("commands.iis.cannot_xp").replace("{x}",String.valueOf(exp))),entity.getUUID());
                    }
                }
                else{
                    entity.sendMessage(new TranslationTextComponent("commands.iis.empty"),entity.getUUID());
                }
            }
            return 0;
        }));
    }

    public static boolean HAS(ListNBT tags){
        for(INBT tag:tags){
            JsonParser jsonParser = new JsonParser();
            //System.out.println(JsonParser.parseString(tag.getAsString().trim()).getAsJsonObject().get("id").getAsString().trim());
            if(jsonParser.parse(tag.getAsString().trim()).getAsJsonObject().get("id").getAsString().trim().equals("minecraft:infinity")){
                return true;
            }
        }
        return false;
    }
}
