package org.example;

import org.example.functions.FunctionSystem;
import org.example.logarithmic.Ln;
import org.example.logarithmic.Log;
import org.example.trigonometric.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        final Cos cos = new Cos();
        CsvWriter.write(
                "csv/cos.csv",
                cos,
                new BigDecimal(-5),
                new BigDecimal(5),
                new BigDecimal("0.1"),
                new BigDecimal("0.0000000001"));

        final Sin sin = new Sin();
        CsvWriter.write(
                "csv/sin.csv",
                sin,
                new BigDecimal(-5),
                new BigDecimal(5),
                new BigDecimal("0.1"),
                new BigDecimal("0.0000000001"));

        final Tan tan = new Tan();
        CsvWriter.write(
                "csv/tan.csv",
                tan,
                new BigDecimal(-5),
                new BigDecimal(5),
                new BigDecimal("0.1"),
                new BigDecimal("0.0000000001"));

        final Sec sec = new Sec();
        CsvWriter.write(
                "csv/sec.csv",
                sec,
                new BigDecimal(-5),
                new BigDecimal(5),
                new BigDecimal("0.1"),
                new BigDecimal("0.0000000001"));

        final Cot cot = new Cot();
        CsvWriter.write(
                "csv/cot.csv",
                cot,
                new BigDecimal(-5),
                new BigDecimal(5),
                new BigDecimal("0.1"),
                new BigDecimal("0.0000000001"));

        final Ln ln = new Ln();
        CsvWriter.write(
                "csv/ln.csv",
                ln,
                new BigDecimal(1),
                new BigDecimal(20),
                new BigDecimal("0.1"),
                new BigDecimal("0.0000000000000000001"));

        final Log log2 = new Log(2);
        CsvWriter.write(
                "csv/log2.csv",
                log2,
                new BigDecimal(1),
                new BigDecimal(20),
                new BigDecimal("0.1"),
                new BigDecimal("0.00000000001"));

        final Log log10 = new Log(10);
        CsvWriter.write(
                "csv/log10.csv",
                log10,
                new BigDecimal(1),
                new BigDecimal(20),
                new BigDecimal("0.1"),
                new BigDecimal("0.00000000001"));


        final FunctionSystem func = new FunctionSystem();
        CsvWriter.write(
                "csv/func.csv",
                func,
                new BigDecimal(-5),
                new BigDecimal(5),
                new BigDecimal("0.1"),
                new BigDecimal("0.00000000001"));

    }}