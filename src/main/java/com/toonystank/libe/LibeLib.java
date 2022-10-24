/*
 *     TaggerNationLib - Common utility library for our products.
 *     Copyright (C) 2022  TaggerNation
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.toonystank.libe;

import com.toonystank.libe.utils.Placeholder;
import io.github.alenalex.adventurelib.spigot.impl.SpigotMessenger;
import lombok.Getter;
import lombok.SneakyThrows;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class LibeLib extends JavaPlugin {

    @Getter
    public String papi;
    @Getter
    public static LibeLib plugin;
    @Getter
    public static Placeholder papiHook;
    @Getter
    public static SpigotMessenger messenger;
    @Getter
    public static MiniMessage miniMessage;

    public void papiExist() {
        if (plugin.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            papi = "HOOKED";
        } else {
            papi = "SKIPPED";
        }
    }

    @SneakyThrows
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        papiHook = new Placeholder();
        messenger = SpigotMessenger
                .builder()
                .setPlugin(this)
                .defaultToMiniMessageTranslator()
                .build();
        miniMessage = MiniMessage.builder().build();
        papiExist();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
