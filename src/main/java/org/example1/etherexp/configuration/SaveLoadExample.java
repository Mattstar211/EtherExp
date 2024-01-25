package org.example1.etherexp.configuration;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.example1.etherexp.EtherExp;

import java.io.*;
import java.util.*;

public class SaveLoadExample implements Serializable {
    public void saveConfigurationToFile(String folderPath, String fileName, EtherExp configuration) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File configFile = new File(folder, fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(configFile))) {
            writer.write("plugin-Version: " + configuration.getDescription().getVersion());
            writer.newLine();
            writer.write("worldBorderSize: " + configuration.getWorldBorderSize());
            writer.newLine();
            writer.write("radius: " + configuration.getRadius());
            writer.newLine();
            writer.write("angle: " + configuration.getAngle());
            writer.newLine();
            writer.write("change_radius: " + configuration.getChange_radius());
            writer.newLine();
            writer.write("change_angle: " + configuration.getChange_angle());
            writer.newLine();
            writer.write("change_angle_percent: " + configuration.getChange_angle_percent());
            writer.newLine();
            writer.write("xLobby: " + configuration.getXLobby());
            writer.newLine();
            writer.write("yLobby: " + configuration.getYLobby());
            writer.newLine();
            writer.write("zLobby: " + configuration.getZLobby());
            writer.newLine();
            writer.write("yawLobby: " + configuration.getYawLobby());
            writer.newLine();
            writer.write("pitchLobby: " + configuration.getPitchLobby());
            writer.newLine();
            writer.write("percentNarrWorld: " + configuration.getPercentNarrWorld());
            writer.newLine();
            writer.write("periodNarrWorld: " + configuration.getPeriodNarrWorld());
            writer.newLine();
            writer.write("xBorder: " + configuration.getXborder());
            writer.newLine();
            writer.write("zBorder: " + configuration.getZborder());
            writer.newLine();
            writer.write("temp_change_angle: " + configuration.getTemp_change_angle());
            writer.newLine();
            writer.write("temp_change_radius: " + configuration.getTemp_change_radius());
            writer.newLine();
            Location position1Portal = configuration.getPosition1Portal();
            Location position2Portal = configuration.getPosition1Portal();
            writer.write("position1Portal: " + position1Portal.getX() + " " + position1Portal.getY() + " " + position1Portal.getZ());
            writer.newLine();
            writer.write("position2Portal: " + position2Portal.getX() + " " + position2Portal.getY() + " " + position2Portal.getZ());
            writer.newLine();
            writer.write("nameBan: ");
            for (String name : configuration.getNameBan()) {
                writer.write(name + ",");
            }
            writer.newLine();
            writer.write("nameAdmin: ");
            for (String name : configuration.getNameAdmin()) {
                writer.write(name + ",");
            }
            writer.newLine();
            System.out.println(ChatColor.GREEN + "Конфигурационный файл сохранен по пути: " + configFile.getAbsolutePath());
        } catch (Exception e) {
            EtherExp.sendErrorMessage(e, this.getClass().getName());
        }
    }
    public void loadConfigurationFromFile(String folderPath, String fileName, EtherExp configuration) {
        File configFile = new File(folderPath, fileName);
        if (!configFile.exists()) {
            System.out.println(ChatColor.YELLOW + "Конфигурационный файл не найден.");
            configuration.updatePlugin();
            saveConfigurationToFile(folderPath, fileName, configuration);
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(": ");
                if (parts.length == 2) {
                    String variableName = parts[0].trim();
                    String variableValue = parts[1].trim();
                    switch (variableName) {
                        case "worldBorderSize":
                            configuration.setWorldBorderSize(Integer.parseInt(variableValue));
                            break;
                        case "radius":
                            configuration.setRadius(Double.parseDouble(variableValue));
                            break;
                        case "angle":
                            configuration.setAngle(Double.parseDouble(variableValue));
                            break;
                        case "change_radius":
                            configuration.setChange_radius(Double.parseDouble(variableValue));
                            break;
                        case "change_angle":
                            configuration.setChange_angle(Double.parseDouble(variableValue));
                            break;
                        case "change_angle_percent":
                            configuration.setChange_angle_percent(Integer.parseInt(variableValue));
                            break;
                        case "xLobby":
                            configuration.setXLobby(Double.parseDouble(variableValue));
                            break;
                        case "yLobby":
                            configuration.setYLobby(Double.parseDouble(variableValue));
                            break;
                        case "zLobby":
                            configuration.setZLobby(Double.parseDouble(variableValue));
                            break;
                        case "yawLobby":
                            configuration.setYawLobby(Float.parseFloat(variableValue));
                            break;
                        case "pitchLobby":
                            configuration.setPitchLobby(Float.parseFloat(variableValue));
                            break;
                        case "xBorder":
                            configuration.setXborder(Double.parseDouble(variableValue));
                            break;
                        case "zBorder":
                            configuration.setZborder(Double.parseDouble(variableValue));
                            break;
                        case "nameBan":
                            String[] namesBan = variableValue.split(",");
                            configuration.setNameBan(new ArrayList<>(Arrays.asList(namesBan)));
                            break;
                        case "nameAdmin":
                            String[] namesAdmin = variableValue.split(",");
                            configuration.setNameAdmin(new ArrayList<>(Arrays.asList(namesAdmin)));
                            break;
                        case "temp_change_angle":
                            configuration.setTemp_change_angle(Double.parseDouble(variableValue));
                            break;
                        case "temp_change_radius":
                            configuration.setTemp_change_radius(Double.parseDouble(variableValue));
                            break;
                        case "percentNarrWorld":
                            configuration.setPercentNarrWorld(Integer.parseInt(variableValue));
                            break;
                        case "periodNarrWorld":
                            configuration.setPeriodNarrWorld(Integer.parseInt(variableValue));
                            break;
                        case "position1Portal":
                            String[] coords = variableValue.split(" ");
                            int x = Integer.parseInt(coords[0]);
                            int y = Integer.parseInt(coords[1]);
                            int z = Integer.parseInt(coords[2]);
                            configuration.setPosition1Portal(x, y, z);
                            break;
                        case "position2Portal":
                            String[] coords1 = variableValue.split(" ");
                            int x1 = Integer.parseInt(coords1[0]);
                            int y1 = Integer.parseInt(coords1[1]);
                            int z1 = Integer.parseInt(coords1[2]);
                            configuration.setPosition1Portal(x1, y1, z1);
                            break;
                    }
                }
            }
            System.out.println(ChatColor.GREEN + "Конфигурационный файл загружен!");
        } catch (Exception e) {
            EtherExp.sendErrorMessage(e, this.getClass().getName());
            System.out.println(ChatColor.RED + "Конфигурационный файл не загружен!");
        }
    }
    public static void updateConfigFile(String filePath, String fieldName, String newValue) {
        try {
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            Map<String, String> properties = new HashMap<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(": ");
                if (parts.length == 2) {
                    properties.put(parts[0], parts[1]);
                }
            }
            reader.close();
            if (properties.containsKey(fieldName)) {
                properties.put(fieldName, newValue);
            } else {
                System.out.println("Поле " + fieldName + " не найдено в файле.");
                return;
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Map.Entry<String, String> entry : properties.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
            writer.close();

            System.out.println("Значение поля " + fieldName + " успешно обновлено.");
        } catch (Exception e) {
            EtherExp.sendErrorMessage(e, "updateConfigFile error");
        }
    }
}
