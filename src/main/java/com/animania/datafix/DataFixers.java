package com.animania.datafix;

import com.animania.datafix.schemas.AnimaniaV0;
import com.animania.datafix.schemas.AnimaniaV1;
import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.DataFixerBuilder;
import com.mojang.datafixers.schemas.Schema;

public class DataFixers {
    public static final String DATA_VERSION_FIELD = "data_version";
    public static final int CURRENT_VERSION = 1;

    private static final DataFixerBuilder.Result DATA_FIXER = createDataFixer();

    private DataFixers() {
    }

    public static DataFixer getDataFixer() {
        return DATA_FIXER.fixer();
    }

    private static DataFixerBuilder.Result createDataFixer() {
        DataFixerBuilder builder = new DataFixerBuilder(CURRENT_VERSION);
        addFixes(builder);
        return builder.build();
    }

    private static void addFixes(DataFixerBuilder builder) {
        builder.addSchema(0, AnimaniaV0::new);

        Schema schema = builder.addSchema(1, AnimaniaV1::new);
    }
}
