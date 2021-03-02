package com.zanny.envirocraft.client.model;

import com.zanny.envirocraft.EnviroCraft;
import com.zanny.envirocraft.world.entity.CamelEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class CamelEntityModel extends AnimatedGeoModel<CamelEntity> {

    @Override
    public Identifier getModelLocation(CamelEntity camelEntity) {
        if (camelEntity.isBaby()) return new Identifier(EnviroCraft.MOD_ID, ("geo/baby_camel.json"));
        return new Identifier(EnviroCraft.MOD_ID, "geo/camel_model.json");
    }

    @Override
    public Identifier getTextureLocation(CamelEntity camelEntity) {
        if (camelEntity.isBaby()) return new Identifier(EnviroCraft.MOD_ID, ("textures/entity/camel/baby_camel.png"));
        return new Identifier(EnviroCraft.MOD_ID, "textures/entity/camel/camel.png");
    }

    @Override
    public Identifier getAnimationFileLocation(CamelEntity camelEntity) {
        if (camelEntity.isBaby()) return new Identifier(EnviroCraft.MOD_ID, ("animations/baby_camel_animations.json"));
        return new Identifier(EnviroCraft.MOD_ID, "animations/camel_animations.json");
    }

    @Override
    public void setLivingAnimations(CamelEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone saddle = this.getAnimationProcessor().getBone("saddle");
        if (saddle != null) {
            if (entity.isSaddled()) {
                saddle.setHidden(false);
            } else {
                saddle.setHidden(true);
            }
        }
    }
}