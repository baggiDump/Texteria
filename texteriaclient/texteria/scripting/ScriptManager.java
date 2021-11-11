package net.xtrafrancyz.mods.texteria.scripting;

import javax.script.Bindings;
import javax.script.CompiledScript;
import javax.script.ScriptException;
import jdk.nashorn.api.scripting.ClassFilter;
import jdk.nashorn.api.scripting.NashornScriptEngine;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;
import net.minecraft.client.Minecraft;
import net.xtrafrancyz.covered.Reflect;
import net.xtrafrancyz.mods.texteria.Texteria;

public class ScriptManager
{
    private final Texteria texteria;
    private final NashornScriptEngine scriptEngine;

    public ScriptManager(Texteria texteria)
    {
        this.texteria = texteria;
        ClassLoader classloader = (ClassLoader)Reflect.invoke(NashornScriptEngineFactory.class, "getAppClassLoader", new Object[0]);
        this.scriptEngine = (NashornScriptEngine)(new NashornScriptEngineFactory()).getScriptEngine(new String[] {"--lazy-compilation=false"}, classloader, (className) ->
        {
            return className.startsWith("net.xtrafrancyz.mods.texteria.util") || className.startsWith("net.xtrafrancyz.mods.texteria.elements") || className.startsWith("net.xtrafrancyz.mods.texteria.gui") || className.equals("net.xtrafrancyz.mods.texteria.Texteria") || className.equals("net.xtrafrancyz.util.ByteMap") || className.equals("net.xtrafrancyz.util.CommonUtils") || className.startsWith("net.minecraft.util.Chat") || className.equals("net.minecraft.util.IChatComponent") || className.equals("net.minecraft.client.renderer.GlStateManager") || className.startsWith("org.lwjgl.input.") || className.startsWith("java.lang.") && !className.substring(10).contains(".") && !className.equals("java.lang.Thread") && !className.equals("java.lang.Runnable") && !className.equals("java.lang.Runtime") && !className.startsWith("java.lang.Proces");
        });

        try
        {
            Bindings bindings = this.newBinginds();
            this.scriptEngine.eval("var a=function(a){var b=Java.type(\"java.lang.String\").valueOf(a[0]);return b;};var b=a([username.length]);b;", bindings);
        }
        catch (ScriptException var4)
        {
            ;
        }
    }

    public CompiledScript compile(String script) throws ScriptException
    {
        long long = System.nanoTime();
        CompiledScript compiledscript = this.scriptEngine.compile(script);
        Texteria.printDebug("Script \n/*******/\n{}\n/*******/\n compiled in {} ns", new Object[] {script, Long.valueOf(System.nanoTime() - long)});
        return compiledscript;
    }

    public Object eval(String script, Bindings bindings) throws ScriptException
    {
        long long = System.nanoTime();
        Object object = this.scriptEngine.eval(script, bindings);
        Texteria.printDebug("Script \n/*******/\n{}\n/*******/\n eval in {} ns", new Object[] {script, Long.valueOf(System.nanoTime() - long)});
        return object;
    }

    public Bindings newBinginds()
    {
        Bindings bindings = this.scriptEngine.createBindings();
        bindings.put((String)"username", Minecraft.getMinecraft().getSession().getUsername());
        bindings.put((String)"texteriaGui", this.texteria.gui);
        bindings.put((String)"texteriaWorld", this.texteria.world);
        return bindings;
    }
}
