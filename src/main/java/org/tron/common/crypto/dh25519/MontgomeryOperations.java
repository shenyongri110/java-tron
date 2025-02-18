package org.tron.common.crypto.dh25519;

public class MontgomeryOperations {

  public static void scalarmult(
      byte[] q, int qoffset,
      byte[] n, int noffset,
      byte[] p, int poffset)
  {
    FieldElement p0=new FieldElement();
    FieldElement q0=new FieldElement();
    FieldOperations.fe_frombytes(p0, p, poffset);
    scalarmult(q0, n, noffset, p0);
    FieldOperations.fe_tobytes(q, qoffset, q0);
  }

  static void scalarmult(
      FieldElement q,
      byte[] n, int noffset,
      FieldElement p)
  {
    byte[] e = new byte[32];//ToDo: remove allocation
    FieldElement x1=new FieldElement();
    FieldElement x2=new FieldElement();
    FieldElement z2=new FieldElement();
    FieldElement x3=new FieldElement();
    FieldElement z3=new FieldElement();
    FieldElement tmp0=new FieldElement();
    FieldElement tmp1=new FieldElement();
    int pos;
    int swap;
    int b;

    for (int i = 0; i < 32; ++i)
      e[i] = n[noffset + i];
    ScalarOperations.sc_clamp(e, 0);
    FieldOperations.fe_copy(x1, p);
    FieldOperations.fe_1(x2);
    FieldOperations.fe_0(z2);
    FieldOperations.fe_copy(x3 , x1);
    FieldOperations.fe_1(z3);

    swap = 0;
    for (pos = 254; pos >= 0; --pos)
    {
      b = e[pos / 8] >>> (pos & 7);
      b &= 1;
      swap ^= b;
      FieldOperations.fe_cswap(x2, x3, swap);
      FieldOperations.fe_cswap(z2, z3, swap);
      swap = b;
      /* qhasm: fe X2 */

      /* qhasm: fe Z2 */

      /* qhasm: fe X3 */

      /* qhasm: fe Z3 */

      /* qhasm: fe X4 */

      /* qhasm: fe Z4 */

      /* qhasm: fe X5 */

      /* qhasm: fe Z5 */

      /* qhasm: fe A */

      /* qhasm: fe B */

      /* qhasm: fe C */

      /* qhasm: fe D */

      /* qhasm: fe E */

      /* qhasm: fe AA */

      /* qhasm: fe BB */

      /* qhasm: fe DA */

      /* qhasm: fe CB */

      /* qhasm: fe t0 */

      /* qhasm: fe t1 */

      /* qhasm: fe t2 */

      /* qhasm: fe t3 */

      /* qhasm: fe t4 */

      /* qhasm: enter ladder */

      /* qhasm: D = X3-Z3 */
      /* asm 1: fe_sub(>D=fe#5,<X3=fe#3,<Z3=fe#4); */
      /* asm 2: fe_sub(>D=tmp0,<X3=x3,<Z3=z3); */
      FieldOperations.fe_sub(tmp0,  x3,  z3);

      /* qhasm: B = X2-Z2 */
      /* asm 1: fe_sub(>B=fe#6,<X2=fe#1,<Z2=fe#2); */
      /* asm 2: fe_sub(>B=tmp1,<X2=x2,<Z2=z2); */
      FieldOperations.fe_sub(tmp1, x2, z2);

      /* qhasm: A = X2+Z2 */
      /* asm 1: fe_add(>A=fe#1,<X2=fe#1,<Z2=fe#2); */
      /* asm 2: fe_add(>A=x2,<X2=x2,<Z2=z2); */
      FieldOperations.fe_add(x2, x2, z2);

      /* qhasm: C = X3+Z3 */
      /* asm 1: fe_add(>C=fe#2,<X3=fe#3,<Z3=fe#4); */
      /* asm 2: fe_add(>C=z2,<X3=x3,<Z3=z3); */
      FieldOperations.fe_add(z2,  x3, z3);

      /* qhasm: DA = D*A */
      /* asm 1: fe_mul(>DA=fe#4,<D=fe#5,<A=fe#1); */
      /* asm 2: fe_mul(>DA=z3,<D=tmp0,<A=x2); */
      FieldOperations.fe_mul(z3, tmp0, x2);

      /* qhasm: CB = C*B */
      /* asm 1: fe_mul(>CB=fe#2,<C=fe#2,<B=fe#6); */
      /* asm 2: fe_mul(>CB=z2,<C=z2,<B=tmp1); */
      FieldOperations.fe_mul(z2,  z2, tmp1);

      /* qhasm: BB = B^2 */
      /* asm 1: fe_sq(>BB=fe#5,<B=fe#6); */
      /* asm 2: fe_sq(>BB=tmp0,<B=tmp1); */
      FieldOperations.fe_sq(tmp0,  tmp1);

      /* qhasm: AA = A^2 */
      /* asm 1: fe_sq(>AA=fe#6,<A=fe#1); */
      /* asm 2: fe_sq(>AA=tmp1,<A=x2); */
      FieldOperations.fe_sq(tmp1,  x2);

      /* qhasm: t0 = DA+CB */
      /* asm 1: fe_add(>t0=fe#3,<DA=fe#4,<CB=fe#2); */
      /* asm 2: fe_add(>t0=x3,<DA=z3,<CB=z2); */
      FieldOperations.fe_add(x3, z3,  z2);

      /* qhasm: assign x3 to t0 */

      /* qhasm: t1 = DA-CB */
      /* asm 1: fe_sub(>t1=fe#2,<DA=fe#4,<CB=fe#2); */
      /* asm 2: fe_sub(>t1=z2,<DA=z3,<CB=z2); */
      FieldOperations.fe_sub(z2, z3,  z2);

      /* qhasm: X4 = AA*BB */
      /* asm 1: fe_mul(>X4=fe#1,<AA=fe#6,<BB=fe#5); */
      /* asm 2: fe_mul(>X4=x2,<AA=tmp1,<BB=tmp0); */
      FieldOperations.fe_mul(x2, tmp1,  tmp0);

      /* qhasm: E = AA-BB */
      /* asm 1: fe_sub(>E=fe#6,<AA=fe#6,<BB=fe#5); */
      /* asm 2: fe_sub(>E=tmp1,<AA=tmp1,<BB=tmp0); */
      FieldOperations.fe_sub(tmp1,  tmp1, tmp0);

      /* qhasm: t2 = t1^2 */
      /* asm 1: fe_sq(>t2=fe#2,<t1=fe#2); */
      /* asm 2: fe_sq(>t2=z2,<t1=z2); */
      FieldOperations.fe_sq(z2, z2);

      /* qhasm: t3 = a24*E */
      /* asm 1: fe_mul121666(>t3=fe#4,<E=fe#6); */
      /* asm 2: fe_mul121666(>t3=z3,<E=tmp1); */
      FieldOperations.fe_mul121666(z3, tmp1);

      /* qhasm: X5 = t0^2 */
      /* asm 1: fe_sq(>X5=fe#3,<t0=fe#3); */
      /* asm 2: fe_sq(>X5=x3,<t0=x3); */
      FieldOperations.fe_sq(x3,  x3);

      /* qhasm: t4 = BB+t3 */
      /* asm 1: fe_add(>t4=fe#5,<BB=fe#5,<t3=fe#4); */
      /* asm 2: fe_add(>t4=tmp0,<BB=tmp0,<t3=z3); */
      FieldOperations.fe_add(tmp0,  tmp0, z3);

      /* qhasm: Z5 = X1*t2 */
      /* asm 1: fe_mul(>Z5=fe#4,x1,<t2=fe#2); */
      /* asm 2: fe_mul(>Z5=z3,x1,<t2=z2); */
      FieldOperations.fe_mul(z3, x1,  z2);

      /* qhasm: Z4 = E*t4 */
      /* asm 1: fe_mul(>Z4=fe#2,<E=fe#6,<t4=fe#5); */
      /* asm 2: fe_mul(>Z4=z2,<E=tmp1,<t4=tmp0); */
      FieldOperations.fe_mul(z2,  tmp1,  tmp0);

      /* qhasm: return */

    }
    FieldOperations.fe_cswap(x2, x3, swap);
    FieldOperations.fe_cswap(z2, z3, swap);

    FieldOperations.fe_invert(z2, z2);
    FieldOperations.fe_mul(x2, x2, z2);
    FieldOperations.fe_copy(q, x2);
    //CryptoBytes.Wipe(e);
  }
}
