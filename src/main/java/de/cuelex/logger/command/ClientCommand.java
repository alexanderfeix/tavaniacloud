package de.cuelex.logger.command;

import de.cuelex.logger.ConsoleLogger;
import de.cuelex.logger.LoggerType;
import de.cuelex.main.TavaniaCloud;
import de.cuelex.user.client.TavaniaClient;
import de.cuelex.user.client.TavaniaClientHandler;

import java.util.concurrent.CompletableFuture;

public class ClientCommand extends Command{
    public ClientCommand(String name, String... alias) {
        super(name, alias);
    }

    @Override
    public void execute(ConsoleLogger logger, String name, String... args) {
        if(args.length == 0){
            ConsoleLogger.getInstance().log(LoggerType.INFORMATION, ClientCommand.class, "Please use:");
            ConsoleLogger.getInstance().log(LoggerType.INFORMATION, ClientCommand.class, "client list | List all Commands");
            ConsoleLogger.getInstance().log(LoggerType.INFORMATION, ClientCommand.class, "client kick <ClientId> | Kicks a client");
            ConsoleLogger.getInstance().log(LoggerType.INFORMATION, ClientCommand.class, "client ban <ClientId> | Bans a client until the client is unbanned");
            ConsoleLogger.getInstance().log(LoggerType.INFORMATION, ClientCommand.class, "client unban <ClientId> | Unbans a client");
        }
        if (args.length == 1){
            if(args[0].equalsIgnoreCase("list")){
                TavaniaCloud.getInstance().getTavaniaClientHandler().listClients();
            }
        }else
        if(args.length == 2){
            int clientId = 0;
            try {
                clientId = Integer.parseInt(args[1]);
            }catch (NumberFormatException numberFormatException){
                ConsoleLogger.getInstance().log(LoggerType.ERROR, ClientCommand.class, "Please type in a valid clientId!");
                return;
            }
            if(args[0].equalsIgnoreCase("kick")){
                try {
                    TavaniaCloud.getInstance().getTavaniaClientHandler().removeClient(clientId);
                    ConsoleLogger.getInstance().log(LoggerType.SUCCESS, ClientCommand.class, "Successfully kicked client with id: " + clientId);
                }catch (Exception exception){
                    //TODO: Create errorlog as logfile!
                    ConsoleLogger.getInstance().log(LoggerType.ERROR, ClientCommand.class, "Could not kick client. Please check the log-file!");
                }
            }else{
                ConsoleLogger.getInstance().log(LoggerType.ERROR, ClientCommand.class, "Unknown command!");
            }
        }else if (args.length == 3){
            int clientId = 0;
            try {
                clientId = Integer.parseInt(args[1]);
            }catch (NumberFormatException numberFormatException){
                ConsoleLogger.getInstance().log(LoggerType.ERROR, ClientCommand.class, "Please type in a valid clientId!");
                return;
            }
             if(args[0].equalsIgnoreCase("ban")){
                 //TODO: No catch when database is disconnected
                try {
                    TavaniaCloud.getInstance().getMySQLBlacklistHandler().banClientAsync(clientId, TavaniaCloud.getInstance().getMySQLBlacklistManager().getClientName(clientId), TavaniaCloud.getInstance().getGregorianDate().toString(), args[2]);
                    ConsoleLogger.getInstance().log(LoggerType.SUCCESS, ClientCommand.class, "Successfully banned client " + clientId);
                }catch (Exception exception){
                    ConsoleLogger.getInstance().log(LoggerType.ERROR, ClientCommand.class, "Could not ban client " + clientId);
                }
             }else if(args[0].equalsIgnoreCase("unban")){
                try {
                    TavaniaCloud.getInstance().getMySQLBlacklistManager().unbanClient(clientId, args[2]);
                    ConsoleLogger.getInstance().log(LoggerType.SUCCESS, ClientCommand.class, "Successfully unbanned client " + clientId);
                }catch (Exception exception){
                    ConsoleLogger.getInstance().log(LoggerType.ERROR, ClientCommand.class, "Could not unban client " + clientId);
                }
             }else{
                 ConsoleLogger.getInstance().log(LoggerType.ERROR, ClientCommand.class, "Unknown command!");
             }
        }else{
            ConsoleLogger.getInstance().log(LoggerType.ERROR, ClientCommand.class, "Unknown command!");
        }
    }
}
