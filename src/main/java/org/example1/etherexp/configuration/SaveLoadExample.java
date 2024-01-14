package org.example1.etherexp.configuration;

import org.example1.etherexp.EtherExp;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SaveLoadExample implements Serializable {
    public void saveConfigurationToFile(String folderPath, String fileName, EtherExp configuration) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs(); // Создаем все несуществующие директории
        }
        File configFile = new File(folder, fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(configFile))) {
            // Записываем значения переменных в файл
            writer.write("#worldBorderSize=" + configuration.getWorldBorderSize());
            writer.newLine();
            writer.write("#radius=" + configuration.getRadius());
            writer.newLine();
            writer.write("#angle=" + configuration.getAngle());
            writer.newLine();
            writer.write("#change_radius=" + configuration.getChange_radius());
            writer.newLine();
            writer.write("#change_angle=" + configuration.getChange_angle());
            writer.newLine();
            writer.write("#change_angle_advancement=" + configuration.getChange_angle_percent());
            writer.newLine();
            writer.write("#xLobby=" + configuration.getXLobby());
            writer.newLine();
            writer.write("#yLobby=" + configuration.getYLobby());
            writer.newLine();
            writer.write("#zLobby=" + configuration.getZLobby());
            writer.newLine();
            writer.write("#yawLobby=" + configuration.getYawLobby());
            writer.newLine();
            writer.write("#pitchLobby=" + configuration.getPitchLobby());
            writer.newLine();
            writer.write("#xBorder=" + configuration.getXborder());
            writer.newLine();
            writer.write("#yBorder=" + configuration.getYborder());
            writer.newLine();
            writer.write("#temp_change_angle=" + configuration.getTemp_change_angle());
            writer.newLine();
            writer.write("#temp_change_radius=" + configuration.getTemp_change_radius());
            writer.newLine();
            writer.write("#nameBan=");
            for (String name : configuration.getNameBan()) {
                writer.write(name + ",");
            }
            writer.newLine();
            writer.write("#nameAdmin=");
            for (String name : configuration.getNameAdmin()) {
                writer.write(name + ",");
            }
            writer.newLine();
            System.out.println("Конфигурационный файл сохранен по пути: " + configFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadConfigurationFromFile(String folderPath,String fileName, EtherExp configuration) {
        File configFile = new File(folderPath, fileName);

        // Проверяем, существует ли файл, и если нет, выходим
        if (!configFile.exists()) {
            System.out.println("Конфигурационный файл не найден.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Разбиваем строку на имя переменной и её значение
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String variableName = parts[0].trim();
                    String variableValue = parts[1].trim();

                    // Устанавливаем значения переменных в соответствии с прочитанными данными
                    switch (variableName) {
                        case "#worldBorderSize":
                            configuration.setWorldBorderSize(Integer.parseInt(variableValue));
                            break;
                        case "#radius":
                            configuration.setRadius(Double.parseDouble(variableValue));
                            break;
                        case "#angle":
                            configuration.setAngle(Double.parseDouble(variableValue));
                            break;
                        case "#change_radius":
                            configuration.setChange_radius(Double.parseDouble(variableValue));
                            break;
                        case "#change_angle":
                            configuration.setChange_angle(Double.parseDouble(variableValue));
                            break;
                        case "#change_angle_advancement":
                            configuration.setChange_angle_percent(Double.parseDouble(variableValue));
                            break;
                        case "#xLobby":
                            configuration.setXLobby(Double.parseDouble(variableValue));
                            break;
                        case "#yLobby":
                            configuration.setYLobby(Double.parseDouble(variableValue));
                            break;
                        case "#zLobby":
                            configuration.setZLobby(Double.parseDouble(variableValue));
                            break;
                        case "#yawLobby":
                            configuration.setYawLobby(Float.parseFloat(variableValue));
                            break;
                        case "#pitchLobby":
                            configuration.setPitchLobby(Float.parseFloat(variableValue));
                            break;
                        case "#xBorder":
                            configuration.setXborder(Double.parseDouble(variableValue));
                            break;
                        case "#yBorder":
                            configuration.setYborder(Double.parseDouble(variableValue));
                            break;
                        case "#nameBan":
                            String[] namesBan = variableValue.split(",");
                            configuration.setNameBan(new ArrayList<>(Arrays.asList(namesBan)));
                            break;
                        case "#nameAdmin":
                            String[] namesAdmin = variableValue.split(",");
                            configuration.setNameAdmin(new ArrayList<>(Arrays.asList(namesAdmin)));
                            break;
                        case "#temp_change_angle":
                            configuration.setTemp_change_angle(Double.parseDouble(variableValue));
                            break;
                        case "#temp_change_radius":
                            configuration.setTemp_change_radius(Double.parseDouble(variableValue));
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
