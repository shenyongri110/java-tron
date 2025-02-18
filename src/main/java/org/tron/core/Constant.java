/*
 * java-tron is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * java-tron is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.tron.core;

import org.tron.common.utils.ByteArray;

public class Constant {

  // whole
  public static final byte[] LAST_HASH = ByteArray.fromString("lastHash");
  public static final String DIFFICULTY = "2001";

  // DB
  public static final String BLOCK_DB_NAME = "block_data";
  public static final String TRANSACTION_DB_NAME = "transaction_data";

  //config for testnet, mainnet, beta
  public static final String TESTNET_CONF = "config.conf";

  //config for junit test
  public static final String TEST_CONF = "config-test.conf";

  public static final String DATABASE_DIR = "storage.directory";

  public static final byte ADD_PRE_FIX_BYTE_MAINNET = (byte) 0x41;   //41 + address
  public static final String ADD_PRE_FIX_STRING_MAINNET = "41";
  public static final byte ADD_PRE_FIX_BYTE_TESTNET = (byte) 0xa0;   //a0 + address
  public static final String ADD_PRE_FIX_STRING_TESTNET = "a0";
  public static final int ADDRESS_SIZE = 42;

  // config for transaction
  public static final long TRANSACTION_MAX_BYTE_SIZE = 500 * 1_024L;
  public static final long MAXIMUM_TIME_UNTIL_EXPIRATION = 24 * 60 * 60 * 1_000L; //one day
  public static final long TRANSACTION_DEFAULT_EXPIRATION_TIME = 60 * 1_000L; //60 seconds
  // config for smart contract
  public static final long SUN_PER_ENERGY = 100; // 1 us = 100 DROP = 100 * 10^-6 TRX
  public static final long ENERGY_LIMIT_IN_CONSTANT_TX = 3_000_000L; // ref: 1 us = 1 energy
  public static final long MAX_RESULT_SIZE_IN_TX = 64; // max 8 * 8 items in result
  public static final long PB_DEFAULT_ENERGY_LIMIT = 0L;
  public static final long CREATOR_DEFAULT_ENERGY_LIMIT = 1000 * 10_000L;


  // Numbers
  public static final int ONE_HUNDRED = 100;
  public static final int ONE_THOUSAND = 1000;

  public static byte[] ZTRON_EXPANDSEED_PERSONALIZATION = {'Z', 't', 'r', 'o', 'n', '_', 'E', 'x',
          'p', 'a', 'n', 'd', 'S', 'e', 'e', 'd'};
  public static int ZC_DIVERSIFIER_SIZE = 11;
  public static int ZC_OUTPUT_DESC_MAX_SIZE = 10;


  /**
   * normal transaction is 0 representing normal transaction unexecuted deferred transaction is 1
   * representing unexecuted deferred transaction executing deferred transaction is 2 representing
   * executing deferred transaction
   */
  public static final int NORMALTRANSACTION = 0;
  public static final int UNEXECUTEDDEFERREDTRANSACTION = 1;
  public static final int EXECUTINGDEFERREDTRANSACTION = 2;
}
