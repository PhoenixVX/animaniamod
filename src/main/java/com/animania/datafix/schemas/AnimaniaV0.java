package com.animania.datafix.schemas;

import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.templates.TypeTemplate;

import java.util.Map;
import java.util.function.Supplier;

public class AnimaniaV0 extends Schema {
    public AnimaniaV0(int versionKey, Schema parent) {
        super(versionKey, parent);
    }

    @Override
    public void registerTypes(Schema schema, Map<String, Supplier<TypeTemplate>> entityTypes, Map<String, Supplier<TypeTemplate>> blockEntityTypes) {
        super.registerTypes(schema, entityTypes, blockEntityTypes);
    }

    @Override
    public void registerSimple(Map<String, Supplier<TypeTemplate>> map, String name) {
        super.registerSimple(map, name);
    }
}
