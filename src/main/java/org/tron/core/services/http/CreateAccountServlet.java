package org.tron.core.services.http;

import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tron.core.Wallet;
import org.tron.protos.Contract.AccountCreateContract;
import org.tron.protos.Protocol.Transaction;
import org.tron.protos.Protocol.Transaction.Contract.ContractType;


@Component
@Slf4j(topic = "API")
public class CreateAccountServlet extends HttpServlet {

  @Autowired
  private Wallet wallet;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    try {
      String contract = request.getReader().lines()
          .collect(Collectors.joining(System.lineSeparator()));
      Util.checkBodySize(contract);
      boolean visible = Util.getVisiblePost(contract);
      AccountCreateContract.Builder build = AccountCreateContract.newBuilder();
      JsonFormat.merge(contract, build, visible);
      Transaction tx = wallet
          .createTransactionCapsule(build.build(), ContractType.AccountCreateContract)
          .getInstance();

      JSONObject input = JSONObject.parseObject(contract);
      tx = Util.setTransactionPermissionId(input, tx);
      response.getWriter().println(Util.printCreateTransaction(tx, visible));
    } catch (Exception e) {
      logger.debug("Exception: {}", e.getMessage());
      try {
        response.getWriter().println(Util.printErrorMsg(e));
      } catch (IOException ioe) {
        logger.debug("IOException: {}", ioe.getMessage());
      }
    }
  }
}